package com.neosage.voting_system.exception;

public class VotacionCerradaException extends RuntimeException {
    public VotacionCerradaException() {
        super("La votación no está habilitada");
    }
}