package com.cricMaster.fantasyICL_backend.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public record CreateFantasyTeamRequest(
        @NotNull
        @Size(min = 1, max = 11, message = "Must select 1–11 players")
        List<@NotNull Long> playerIds,

        @NotNull(message = "Captain must be specified")
        Long captainId,

        @NotNull(message = "Vice-captain must be specified")
        Long viceCaptainId
) {}
