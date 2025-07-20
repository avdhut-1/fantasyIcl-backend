package com.cricMaster.fantasyICL_backend.dto;

import java.util.List;

public record SimpleTeamDto(
        Long                      id,
        String                    name,
        List<PlayerSelectionDto>  players
) {}
