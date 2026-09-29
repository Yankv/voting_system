package com.neosage.voting_system.dto.response;

import java.math.BigDecimal;

public record IndicadoresDTO(
        long totalVotos,
        long totalVotantesHabilitados,
        BigDecimal porcentajeParticipacion,
        ResultadoCandidatoDTO candidatoLider,
        boolean abierta
) {

}
