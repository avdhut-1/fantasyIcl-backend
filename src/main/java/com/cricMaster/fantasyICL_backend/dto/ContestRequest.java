package com.cricMaster.fantasyICL_backend.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record ContestRequest(
        @NotNull(message="teamAId is required")
        Long teamAId,

        @NotNull(message="teamBId is required")
        Long teamBId,

        @NotNull(message="matchDate is required")
        @FutureOrPresent(message="matchDate cannot be in the past")
        LocalDate matchDate,

        @NotNull(message="matchTime is required")
        LocalTime matchTime,

        @NotNull @Size(min=2, max=100, message="venue must be 2–100 chars")
        String venue
) {}
