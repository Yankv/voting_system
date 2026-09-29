package com.neosage.voting_system.exception;

public class TokenInvalidoException extends RuntimeException {
    public TokenInvalidoException() {
        super("La sesión no es válida, por favor ingrese de nuevo");
    }
}
