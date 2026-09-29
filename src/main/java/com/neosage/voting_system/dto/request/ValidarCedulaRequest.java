package com.neosage.voting_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ValidarCedulaRequest(
        @NotBlank(message = "La cédula es obligatoria") 
        @Pattern (regexp = "^[0-9]{6,10}$", message = "La cédula no es válida") 
        String cedula
) {

}
