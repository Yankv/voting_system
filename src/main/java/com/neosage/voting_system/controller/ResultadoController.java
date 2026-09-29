package com.neosage.voting_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosage.voting_system.dto.response.DashboardResultadosDTO;
import com.neosage.voting_system.service.ResultadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/resultados")
@RequiredArgsConstructor
public class ResultadoController {

    private final ResultadoService resultadoService;

    @GetMapping
    public ResponseEntity<DashboardResultadosDTO> obtenerResultados() {
        return ResponseEntity.ok(resultadoService.obtenerDashboard());
    }
}
