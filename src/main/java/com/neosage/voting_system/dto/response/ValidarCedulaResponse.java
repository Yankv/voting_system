package com.neosage.voting_system.dto.response;

public record ValidarCedulaResponse(
        boolean habilitadoParaVotar,
        String mensaje,
        String token
) {
}

