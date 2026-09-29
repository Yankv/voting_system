package com.neosage.voting_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosage.voting_system.dto.response.EstadoVotacionDTO;
import com.neosage.voting_system.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/votacion")
@RequiredArgsConstructor
public class VotacionController {

    private final AdminService adminService;

    @GetMapping("/estado")
    public ResponseEntity<EstadoVotacionDTO> obtenerEstado() {
        return ResponseEntity.ok(adminService.obtenerEstado());
    }
}
