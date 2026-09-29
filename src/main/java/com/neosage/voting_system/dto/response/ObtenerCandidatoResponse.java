package com.neosage.voting_system.dto.response;

public record ObtenerCandidatoResponse(
        Long id,
        String primerNombre,
        String segundoNombre,
        String primerApellido,
        String segundoApellido,
        Integer numeroTarjeton,
        String fotoUrl
) {

}
