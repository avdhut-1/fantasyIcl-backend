package com.cricMaster.fantasyICL_backend.dto;

import java.util.List;

public record FantasyTeamResponse(
        Long teamId,
        Long contestId,
        List<Long> playerIds
) {}
