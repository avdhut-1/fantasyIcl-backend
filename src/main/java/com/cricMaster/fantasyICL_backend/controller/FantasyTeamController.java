package com.cricMaster.fantasyICL_backend.controller;

import com.cricMaster.fantasyICL_backend.dto.CreateFantasyTeamRequest;
import com.cricMaster.fantasyICL_backend.dto.FantasyTeamResponse;
import com.cricMaster.fantasyICL_backend.security.UserPrincipal;
import com.cricMaster.fantasyICL_backend.service.FantasyTeamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contests/{contestId}/teams")
public class FantasyTeamController {

    private final FantasyTeamService fantasyTeamService;

    public FantasyTeamController(FantasyTeamService fantasyTeamService) {
        this.fantasyTeamService = fantasyTeamService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FantasyTeamResponse joinContest(
            @PathVariable("contestId") Long contestId,
            @Valid @RequestBody CreateFantasyTeamRequest req,
            @AuthenticationPrincipal UserPrincipal me
            ) {
        Long userId = me.getId();
        return fantasyTeamService.createTeam(contestId, userId, req);
    }
}
