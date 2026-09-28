package com.neosage.voting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosage.voting_system.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    long countByCandidatoId(Long candidatoId);
}
