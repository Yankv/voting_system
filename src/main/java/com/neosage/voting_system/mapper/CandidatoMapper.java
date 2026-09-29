package com.neosage.voting_system.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.neosage.voting_system.dto.response.ObtenerCandidatoResponse;
import com.neosage.voting_system.dto.request.CrearCandidatoRequest;
import com.neosage.voting_system.model.Candidato;

@Mapper(componentModel = "spring")
public interface CandidatoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping (target = "activo", ignore = true)
    Candidato toEntity(CrearCandidatoRequest crearCandidato);

    ObtenerCandidatoResponse toDto(Candidato candidato);

    List<ObtenerCandidatoResponse> toDtoList(List<Candidato> candidatos);
}
