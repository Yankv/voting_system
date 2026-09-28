package com.neosage.voting_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosage.voting_system.model.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    List<Candidato> findByActivoTrueOrderByNumeroTarjetonAsc();

    Optional<Candidato> findByIdAndActivoTrue(Long id);

    boolean existsByNumeroTarjeton(Integer numeroTarjeton);
}
