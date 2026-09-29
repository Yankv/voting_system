package com.neosage.voting_system.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosage.voting_system.dto.response.DashboardResultadosDTO;
import com.neosage.voting_system.dto.response.EstadoVotacionDTO;
import com.neosage.voting_system.dto.response.IndicadoresDTO;
import com.neosage.voting_system.dto.response.PuntoTendenciaDTO;
import com.neosage.voting_system.dto.response.ResultadoCandidatoDTO;
import com.neosage.voting_system.mapper.ConfiguracionVotacionMapper;
import com.neosage.voting_system.mapper.ResultadoCandidatoMapper;
import com.neosage.voting_system.model.ConfiguracionVotacion;
import com.neosage.voting_system.projection.VotosPorMinutoProjection;
import com.neosage.voting_system.repository.ConfiguracionVotacionRepository;
import com.neosage.voting_system.repository.ResultadoCandidatoRepository;
import com.neosage.voting_system.repository.VotanteRepository;
import com.neosage.voting_system.repository.VotoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultadoService {
    private final ResultadoCandidatoRepository resultadoCandidatoRepository;
    private final ResultadoCandidatoMapper resultadoCandidatoMapper;
    private final VotoRepository votoRepository;
    private final VotanteRepository votanteRepository;
    private final ConfiguracionVotacionRepository configuracionRepository;
    private final ConfiguracionVotacionMapper configuracionMapper;

    @Transactional(readOnly = true)
    public DashboardResultadosDTO obtenerDashboard() {
        List<ResultadoCandidatoDTO> ranking = resultadoCandidatoMapper.toDtoList(
                resultadoCandidatoRepository.findAllByOrderByTotalVotosDescNumeroTarjetonAsc());

        EstadoVotacionDTO estado = obtenerEstado();
        IndicadoresDTO indicadores = construirIndicadores(ranking, estado);
        List<PuntoTendenciaDTO> tendencia = construirTendencia();

        return new DashboardResultadosDTO(estado, indicadores, ranking, tendencia);
    }

    private IndicadoresDTO construirIndicadores(List<ResultadoCandidatoDTO> ranking, EstadoVotacionDTO estado) {
        long totalVotos = votoRepository.count();
        long totalVotantesHabilitados = votanteRepository.count();

        BigDecimal porcentajeParticipacion = totalVotantesHabilitados == 0
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(totalVotos)
                        .multiply(BigDecimal.valueOf(100))
                        .divide(BigDecimal.valueOf(totalVotantesHabilitados), 2, RoundingMode.HALF_UP);

        // Sin líder mientras no haya votos, aunque el ranking siempre
        // traiga filas (candidatos con 0 votos incluidos).
        ResultadoCandidatoDTO candidatoLider = (totalVotos > 0 && !ranking.isEmpty())
                ? ranking.get(0)
                : null;

        return new IndicadoresDTO(
                totalVotos,
                totalVotantesHabilitados,
                porcentajeParticipacion,
                candidatoLider,
                estado.abierta());
    }

    private List<PuntoTendenciaDTO> construirTendencia() {
        List<PuntoTendenciaDTO> puntos = new ArrayList<>();
        long acumulado = 0;

        for (VotosPorMinutoProjection fila : votoRepository.contarVotosPorMinuto()) {
            acumulado += fila.getVotos();
            puntos.add(new PuntoTendenciaDTO(fila.getMinuto(), fila.getVotos(), acumulado));
        }
        return puntos;
    }

    private EstadoVotacionDTO obtenerEstado() {
        ConfiguracionVotacion configuracion = configuracionRepository
                .findById(ConfiguracionVotacion.ID_UNICO)
                .orElseThrow(() -> new IllegalStateException("No existe configuración de votación"));
        return configuracionMapper.toDto(configuracion);
    }
}
