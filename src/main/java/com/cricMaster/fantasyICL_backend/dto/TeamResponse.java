package com.cricMaster.fantasyICL_backend.dto;

import java.util.List;

public record TeamResponse(
        Long id,
        String name,
        String logoUrl,
        String ownerName,
        List<PlayerMinDto> roster
) {}
