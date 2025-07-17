package com.cricMaster.fantasyICL_backend.controller;

import com.cricMaster.fantasyICL_backend.dto.TeamRequest;
import com.cricMaster.fantasyICL_backend.dto.TeamResponse;
import com.cricMaster.fantasyICL_backend.dto.TournamentRequest;
import com.cricMaster.fantasyICL_backend.dto.TournamentResponse;
import com.cricMaster.fantasyICL_backend.service.AdminTournamentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/tournaments")
public class AdminTournamentController {

    private final AdminTournamentService svc;

    public AdminTournamentController(AdminTournamentService svc) {
        this.svc = svc;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TournamentResponse createTournament(
            @Valid @RequestBody TournamentRequest req) {
        return svc.createTournament(req);
    }

    @PostMapping("/{tournamentId}/teams")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponse createTeam(
            @PathVariable("tournamentId") Long tournamentId,
            @Valid @RequestBody TeamRequest req) {
        return svc.createTeam(tournamentId, req);
    }

    @PostMapping("/{tournamentId}/teams/{teamId}/players/{playerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPlayer(
            @PathVariable("tournamentId")    Long tournamentId,
            @PathVariable("teamId") Long teamId,
            @PathVariable("playerId")    Long playerId) {
        svc.assignPlayer(tournamentId, teamId, playerId);
    }

    @GetMapping("/{tournamentId}/teams")
    public List<TeamResponse> listTeams(
            @PathVariable("tournamentId") Long tournamentId) {
        return svc.listTeams(tournamentId);
    }
}