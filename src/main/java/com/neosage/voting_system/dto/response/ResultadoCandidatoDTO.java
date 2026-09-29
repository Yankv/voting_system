package com.neosage.voting_system.dto.response;

import java.math.BigDecimal;

public record ResultadoCandidatoDTO(
        Long candidatoId,
        Integer numeroTarjeton,
        String primerNombre,
        String segundoNombre,
        String primerApellido,
        String segundoApellido,
        String fotoUrl,
        Long totalVotos,
        BigDecimal porcentaje,
        Long totalGeneral
) {

}
