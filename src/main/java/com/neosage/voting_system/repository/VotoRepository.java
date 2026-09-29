package com.neosage.voting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.neosage.voting_system.model.Voto;
import com.neosage.voting_system.projection.VotosPorMinutoProjection;
import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    long countByCandidatoId(Long candidatoId);

    @Query(value = """
            SELECT date_trunc('minute', fecha_hora) AS minuto, COUNT(*) AS votos
              FROM voto
             GROUP BY date_trunc('minute', fecha_hora)
             ORDER BY minuto
            """, nativeQuery = true)
    List<VotosPorMinutoProjection> contarVotosPorMinuto();
}
