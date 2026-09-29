package com.neosage.voting_system.dto.response;

import java.util.List;

public record ActualizacionResultadosDTO(
        EstadoVotacionDTO estado,
        List<ResultadoCandidatoDTO> resultados
) {

}
