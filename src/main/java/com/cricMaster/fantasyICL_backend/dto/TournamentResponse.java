package com.cricMaster.fantasyICL_backend.dto;

import java.time.LocalDate;

public record TournamentResponse(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        String location,
        String ballType
) {}
