package com.neosage.voting_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosage.voting_system.dto.request.CrearCandidatoRequest;
import com.neosage.voting_system.dto.request.CrearUsuario;
import com.neosage.voting_system.dto.request.LoginRequest;
import com.neosage.voting_system.dto.response.EstadoVotacionDTO;
import com.neosage.voting_system.dto.response.LoginResponse;
import com.neosage.voting_system.dto.response.UsuarioAdminDTO;
import com.neosage.voting_system.service.AdminService;
import com.neosage.voting_system.service.CandidatoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final CandidatoService candidatoService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(adminService.login(request));
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioAdminDTO> registrarAdmin(
            @Valid @RequestBody CrearUsuario request) {
        UsuarioAdminDTO creado = adminService.registrarAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PostMapping("/votacion/abrir")
    public ResponseEntity<EstadoVotacionDTO> abrirVotacion() {
        return ResponseEntity.ok(adminService.abrirVotacion());
    }

    @PostMapping("/votacion/cerrar")
    public ResponseEntity<EstadoVotacionDTO> cerrarVotacion() {
        return ResponseEntity.ok(adminService.cerrarVotacion());
    }

    @PostMapping("/crear-candidato")
    public boolean crearCandidato(@RequestBody CrearCandidatoRequest request) {
        return candidatoService.crearCandidato(request);
    }
}
