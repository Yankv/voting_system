package com.neosage.voting_system.service;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosage.voting_system.dto.request.RegistrarVotoRequest;
import com.neosage.voting_system.dto.response.RegistrarVotoResponse;
import com.neosage.voting_system.dto.response.ValidarCedulaResponse;
import com.neosage.voting_system.exception.CandidatoNoValidoException;
import com.neosage.voting_system.exception.CedulaYaVotoException;
import com.neosage.voting_system.exception.VotacionCerradaException;
import com.neosage.voting_system.jwt.JwtService;
import com.neosage.voting_system.model.Candidato;
import com.neosage.voting_system.model.Votante;
import com.neosage.voting_system.model.Voto;
import com.neosage.voting_system.repository.CandidatoRepository;
import com.neosage.voting_system.repository.ConfiguracionVotacionRepository;
import com.neosage.voting_system.repository.VotanteRepository;
import com.neosage.voting_system.repository.VotoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VotacionService {
    private final VotanteRepository votanteRepository;
    private final CandidatoRepository candidatoRepository;
    private final VotoRepository votoRepository;
    private final ConfiguracionVotacionRepository configuracionRepository;
    private final JwtService jwtService;
    private final ResultadosPublisher resultadosPublisher;

    /**
     * RF-01, RF-02, RF-03, RF-04.
     * No lanza excepción por reglas de negocio: el resultado con
     * habilitadoParaVotar = false y su mensaje es la respuesta esperada
     * para que el frontend la muestre tal cual.
     */
    @Transactional(readOnly = true)
    public ValidarCedulaResponse validarCedula(String cedula) {
        Votante votante = votanteRepository.findByCedula(cedula).orElse(null);

        if (votante == null) {
            return new ValidarCedulaResponse(false, "La cédula no es válida", null);
        }
        if (votante.isHaVotado()) {
            return new ValidarCedulaResponse(false, "Esta cédula ya registró su voto", null);
        }
        if (!configuracionRepository.estaAbierta()) {
            return new ValidarCedulaResponse(false, "La votación no está habilitada", null);
        }

        String token = jwtService.generarTokenVotante(cedula);
        return new ValidarCedulaResponse(true, "Cédula validada, puede continuar", token);
    }

    @Transactional
    public RegistrarVotoResponse registrarVoto(String tokenVotante, RegistrarVotoRequest request) {
        String cedula = jwtService.extraerCedulaVotante(tokenVotante);

        if (!configuracionRepository.estaAbierta()) {
            throw new VotacionCerradaException();
        }

        Candidato candidato = candidatoRepository.findByIdAndActivoTrue(request.candidatoId())
                .orElseThrow(CandidatoNoValidoException::new);

        int filasActualizadas = votanteRepository.marcarComoVotado(cedula, Instant.now());
        if (filasActualizadas == 0) {
            throw new CedulaYaVotoException();
        }

        Voto voto = new Voto();
        voto.setCandidato(candidato);
        votoRepository.save(voto);

        resultadosPublisher.publicarActualizacion();

        return new RegistrarVotoResponse(true, "Voto registrado correctamente. Gracias por participar.");
    }
}
