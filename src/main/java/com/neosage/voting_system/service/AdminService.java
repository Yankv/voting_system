package com.neosage.voting_system.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosage.voting_system.dto.request.CrearUsuario;
import com.neosage.voting_system.dto.request.LoginRequest;
import com.neosage.voting_system.dto.response.EstadoVotacionDTO;
import com.neosage.voting_system.dto.response.LoginResponse;
import com.neosage.voting_system.dto.response.UsuarioAdminDTO;
import com.neosage.voting_system.exception.CredencialesInvalidasException;
import com.neosage.voting_system.jwt.JwtService;
import com.neosage.voting_system.mapper.ConfiguracionVotacionMapper;
import com.neosage.voting_system.mapper.UsuarioMapper;
import com.neosage.voting_system.model.ConfiguracionVotacion;
import com.neosage.voting_system.model.Usuario;
import com.neosage.voting_system.repository.ConfiguracionVotacionRepository;
import com.neosage.voting_system.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final ConfiguracionVotacionRepository configuracionRepository;
    private final ConfiguracionVotacionMapper configuracionMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ResultadosPublisher resultadosPublisher;

    @Transactional
    public UsuarioAdminDTO registrarAdmin(CrearUsuario request) {
        // if (usuarioAdminRepository.existsByUsername(request.username())) {
        //     throw new AdminYaExisteException();
        // }
 
        Usuario admin = Usuario.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .activo(true)
                .build();
 
        Usuario guardado = usuarioRepository.save(admin);
        return usuarioMapper.toDto(guardado);
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        Usuario admin = usuarioRepository
                .findByUsernameAndActivoTrue(request.username())
                .orElseThrow(CredencialesInvalidasException::new);

        if (!passwordEncoder.matches(request.password(), admin.getPassword())) {
            throw new CredencialesInvalidasException();
        }

        String token = jwtService.generarTokenAdmin(admin.getUsername());
        return new LoginResponse(token, admin.getUsername());
    }

    @Transactional
    public EstadoVotacionDTO abrirVotacion() {
        configuracionRepository.cambiarEstado(true);
        resultadosPublisher.publicarActualizacion();
        return obtenerEstado();
    }

    @Transactional
    public EstadoVotacionDTO cerrarVotacion() {
        configuracionRepository.cambiarEstado(false);
        resultadosPublisher.publicarActualizacion();
        return obtenerEstado();
    }

    @Transactional(readOnly = true)
    public EstadoVotacionDTO obtenerEstado() {
        ConfiguracionVotacion configuracion = configuracionRepository
                .findById(ConfiguracionVotacion.ID_UNICO)
                .orElseThrow(() -> new IllegalStateException("No existe configuración de votación"));
        return configuracionMapper.toDto(configuracion);
    }
}