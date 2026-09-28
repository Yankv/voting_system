package com.neosage.voting_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosage.voting_system.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsernameAndActivoTrue(String username);

    boolean existsByUsername(String username);
}
