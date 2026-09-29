package com.neosage.voting_system.dto.response;

import java.time.Instant;

public record EstadoVotacionDTO(
        boolean abierta,
        Instant fechaApertura,
        Instant fechaCierre
) {

}
