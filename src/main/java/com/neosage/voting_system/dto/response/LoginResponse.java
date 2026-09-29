package com.neosage.voting_system.dto.response;

public record LoginResponse(
        String token,
        String username
) {

}
