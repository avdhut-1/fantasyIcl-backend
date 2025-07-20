package com.cricMaster.fantasyICL_backend.dto;

public record AvailablePlayersResponse(
        Long           contestId,
        SimpleTeamDto  teamA,
        SimpleTeamDto  teamB
) {}
