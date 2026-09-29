package com.neosage.voting_system.mapper;

import org.mapstruct.Mapper;

import com.neosage.voting_system.dto.response.EstadoVotacionDTO;
import com.neosage.voting_system.model.ConfiguracionVotacion;

@Mapper(componentModel = "spring")
public interface ConfiguracionVotacionMapper {

    EstadoVotacionDTO toDto(ConfiguracionVotacion configuracion);
}
