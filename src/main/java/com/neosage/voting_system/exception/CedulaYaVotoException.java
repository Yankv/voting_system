package com.neosage.voting_system.exception;

public class CedulaYaVotoException extends RuntimeException {
    public CedulaYaVotoException() {
        super("Esta cédula ya registró su voto");
    }
}
