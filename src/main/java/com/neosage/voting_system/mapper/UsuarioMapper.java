package com.neosage.voting_system.mapper;

import org.mapstruct.Mapper;

import com.neosage.voting_system.dto.response.UsuarioAdminDTO;
import com.neosage.voting_system.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioAdminDTO toDto(Usuario usuarioAdmin);
}
