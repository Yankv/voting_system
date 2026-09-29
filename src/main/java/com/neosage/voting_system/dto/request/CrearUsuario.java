package com.neosage.voting_system.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CrearUsuario(
    @NotBlank(message = "El usuario es obligatorio") String username,
    @NotBlank(message = "La contraseña es obligatoria") String password
) {

}
