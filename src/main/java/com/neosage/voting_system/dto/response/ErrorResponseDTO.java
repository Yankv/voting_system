package com.neosage.voting_system.dto.response;

import java.time.Instant;

public record ErrorResponseDTO(
        Instant timestamp,
        int status,
        String mensaje) {
    public static ErrorResponseDTO of(int status, String mensaje) {
        return new ErrorResponseDTO(Instant.now(), status, mensaje);
    }
}
