package com.neosage.voting_system.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.neosage.voting_system.dto.response.ResultadoCandidatoDTO;
import com.neosage.voting_system.model.ResultadoCandidato;

@Mapper(componentModel = "spring")
public interface ResultadoCandidatoMapper {

    ResultadoCandidatoDTO toDto(ResultadoCandidato resultado);

    List<ResultadoCandidatoDTO> toDtoList(List<ResultadoCandidato> resultados);
}
