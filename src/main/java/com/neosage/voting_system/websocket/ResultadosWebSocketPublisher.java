package com.neosage.voting_system.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosage.voting_system.service.ResultadoService;
import com.neosage.voting_system.service.ResultadosPublisher;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultadosWebSocketPublisher implements ResultadosPublisher {
    private static final String DESTINO = "/topic/resultados";

    private final SimpMessagingTemplate messagingTemplate;
    private final ResultadoService resultadoService;

    @Override
    @Transactional(readOnly = true)
    public void publicarActualizacion() {
        messagingTemplate.convertAndSend(DESTINO, resultadoService.obtenerDashboard());
    }

    // private final SimpMessagingTemplate messagingTemplate;
    // private final ResultadoService resultadoService;
    // private final ConfiguracionVotacionRepository configuracionRepository;
    // private final ConfiguracionVotacionMapper configuracionMapper;

    // @Override
    // @Transactional(readOnly = true)
    // public void publicarActualizacion() {
    // ActualizacionResultadosDTO actualizacion = new ActualizacionResultadosDTO(
    // obtenerEstado(),
    // resultadoService.obtenerResultados());

    // messagingTemplate.convertAndSend(DESTINO, actualizacion);
    // }

    // private EstadoVotacionDTO obtenerEstado() {
    // ConfiguracionVotacion configuracion = configuracionRepository
    // .findById(ConfiguracionVotacion.ID_UNICO)
    // .orElseThrow(() -> new IllegalStateException("No existe configuración de
    // votación"));
    // return configuracionMapper.toDto(configuracion);
    // }
}
