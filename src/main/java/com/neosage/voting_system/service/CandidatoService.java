package com.neosage.voting_system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosage.voting_system.repository.CandidatoRepository;
import com.neosage.voting_system.mapper.CandidatoMapper;
import com.neosage.voting_system.model.Candidato;
import com.neosage.voting_system.dto.request.CrearCandidatoRequest;
import com.neosage.voting_system.dto.response.ObtenerCandidatoResponse;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CandidatoService {
    private final CandidatoRepository candidatoRepository;
    private final CandidatoMapper candidatoMapper;
 
    @Transactional(readOnly = true)
    public List<ObtenerCandidatoResponse> listarCandidatos() {
        List<Candidato> candidatos = candidatoRepository.findAll();
        return candidatoMapper.toDtoList(candidatos);
    }

    public boolean crearCandidato(CrearCandidatoRequest candidato) {
        if (candidatoRepository.existsByNumeroTarjeton(candidato.numeroTarjeton())) {
            return false;
        }
        candidatoRepository.save(candidatoMapper.toEntity(candidato));
        return true;
    }
}
