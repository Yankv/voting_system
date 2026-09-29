package com.neosage.voting_system.exception;

public class CandidatoNoValidoException extends RuntimeException {
    public CandidatoNoValidoException() {
        super("El candidato seleccionado no es válido");
    }
}
