package com.neosage.voting_system.dto.response;

import java.time.Instant;

public record PuntoTendenciaDTO(
        Instant minuto,
        long votosEnMinuto,
        long votosAcumulados
) {

}
