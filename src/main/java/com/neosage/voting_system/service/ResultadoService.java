package com.neosage.voting_system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosage.voting_system.dto.response.ResultadoCandidatoDTO;
import com.neosage.voting_system.mapper.ResultadoCandidatoMapper;
import com.neosage.voting_system.repository.ResultadoCandidatoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultadoService {

    private final ResultadoCandidatoRepository resultadoCandidatoRepository;
    private final ResultadoCandidatoMapper resultadoCandidatoMapper;

    @Transactional(readOnly = true)
    public List<ResultadoCandidatoDTO> obtenerResultados() {
        return resultadoCandidatoMapper.toDtoList(
                resultadoCandidatoRepository.findAllByOrderByTotalVotosDescNumeroTarjetonAsc());
    }
}
