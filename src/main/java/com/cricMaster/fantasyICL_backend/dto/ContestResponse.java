package com.cricMaster.fantasyICL_backend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ContestResponse(
        Long id,
        Long tournamentId,
        Long teamAId,
        String teamAName,
        Long teamBId,
        String teamBName,
        LocalDate matchDate,
        LocalTime matchTime,
        String venue
) {}
