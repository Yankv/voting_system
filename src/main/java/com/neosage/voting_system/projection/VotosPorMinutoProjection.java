package com.neosage.voting_system.projection;

import java.time.Instant;

public interface VotosPorMinutoProjection {
    Instant getMinuto();
    Long getVotos();
}
