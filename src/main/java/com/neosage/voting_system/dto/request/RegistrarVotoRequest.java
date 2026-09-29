package com.neosage.voting_system.dto.request;

import jakarta.validation.constraints.NotNull;

public record RegistrarVotoRequest(
        @NotNull(message = "Debe seleccionar un candidato") 
        Long candidatoId
) {

}
