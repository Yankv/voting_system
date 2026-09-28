package com.neosage.voting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neosage.voting_system.model.ConfiguracionVotacion;

public interface ConfiguracionVotacionRepository extends JpaRepository<ConfiguracionVotacion, Short> {
    @Query("SELECT c.abierta FROM ConfiguracionVotacion c WHERE c.id = 1")
    boolean estaAbierta();

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE ConfiguracionVotacion c SET c.abierta = :abierta WHERE c.id = 1")
    int cambiarEstado(@Param("abierta") boolean abierta);
}
