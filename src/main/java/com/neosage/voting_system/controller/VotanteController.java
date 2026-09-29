package com.neosage.voting_system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosage.voting_system.dto.request.RegistrarVotoRequest;
import com.neosage.voting_system.dto.request.ValidarCedulaRequest;
import com.neosage.voting_system.dto.response.ObtenerCandidatoResponse;
import com.neosage.voting_system.dto.response.RegistrarVotoResponse;
import com.neosage.voting_system.dto.response.ValidarCedulaResponse;
import com.neosage.voting_system.exception.TokenInvalidoException;
import com.neosage.voting_system.service.CandidatoService;
import com.neosage.voting_system.service.VotacionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/votantes")
@RequiredArgsConstructor
public class VotanteController {
    private static final String PREFIJO_BEARER = "Bearer ";

    private final VotacionService votacionService;
    private final CandidatoService candidatoService;

    @GetMapping("/candidatos")
    public ResponseEntity<List<ObtenerCandidatoResponse>> listarCandidatos() {
        return ResponseEntity.ok(candidatoService.listarCandidatos());
    }

    @PostMapping("/validar-cedula")
    public ResponseEntity<ValidarCedulaResponse> validarCedula(
            @Valid @RequestBody ValidarCedulaRequest request) {
        return ResponseEntity.ok(votacionService.validarCedula(request.cedula()));
    }

    @PostMapping("/voto")
    public ResponseEntity<RegistrarVotoResponse> registrarVoto(
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid @RequestBody RegistrarVotoRequest request) {
        String token = extraerToken(authorizationHeader);
        return ResponseEntity.ok(votacionService.registrarVoto(token, request));
    }

    private String extraerToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(PREFIJO_BEARER)) {
            throw new TokenInvalidoException();
        }
        return authorizationHeader.substring(PREFIJO_BEARER.length());
    }
}
