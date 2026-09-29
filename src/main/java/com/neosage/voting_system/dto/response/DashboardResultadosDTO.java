package com.neosage.voting_system.dto.response;

import java.util.List;

public record DashboardResultadosDTO(
        EstadoVotacionDTO estado,
        IndicadoresDTO indicadores,
        List<ResultadoCandidatoDTO> ranking,
        List<PuntoTendenciaDTO> tendencia
) {

}
