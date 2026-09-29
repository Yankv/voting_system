package com.neosage.voting_system.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CrearCandidatoRequest(
        @NotBlank(message = "El primer nombre es obligatorio") String primerNombre,
        String segundoNombre,
        @NotBlank(message = "El primer apellido es obligatorio") String primerApellido,
        String segundoApellido,
        @NotBlank(message = "El número de tarjeton es obligatorio") Integer numeroTarjeton,
        String fotoUrl
) {

}
