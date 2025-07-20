package com.cricMaster.fantasyICL_backend.dto;

import java.math.BigDecimal;

public record PlayerSelectionDto(
        Long    id,
        String  name,
        String  avatarUrl,
        BigDecimal creditScore
) {}
