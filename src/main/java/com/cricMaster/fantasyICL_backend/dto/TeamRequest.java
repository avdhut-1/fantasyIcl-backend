package com.cricMaster.fantasyICL_backend.dto;

import jakarta.validation.constraints.NotBlank;

public record TeamRequest(
        @NotBlank String name,
        String logoUrl,
        @NotBlank String ownerName
) {}
