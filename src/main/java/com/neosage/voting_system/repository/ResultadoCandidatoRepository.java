package com.neosage.voting_system.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.neosage.voting_system.model.ResultadoCandidato;

public interface ResultadoCandidatoRepository extends Repository<ResultadoCandidato, Long> {
    List<ResultadoCandidato> findAllByOrderByTotalVotosDescNumeroTarjetonAsc();
}
