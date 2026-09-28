package com.neosage.voting_system.repository;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neosage.voting_system.model.Votante;

public interface VotanteRepository extends JpaRepository<Votante, Long> {
    Optional<Votante> findByCedula(String cedula);

    boolean existsByCedula(String cedula);

    /**
     * Marca al votante como "ya votó" de forma atómica.
     * Devuelve 1 si lo marcó; 0 si la cédula no existe o ya había votado.
     * Debe ejecutarse dentro de la misma transacción que el INSERT del voto.
     */
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("""
            UPDATE Votante v
               SET v.haVotado = true, v.fechaVoto = :fecha
             WHERE v.cedula = :cedula AND v.haVotado = false
            """)
    int marcarComoVotado(@Param("cedula") String cedula,
            @Param("fecha") Instant fecha);
}
