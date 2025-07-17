package com.cricMaster.fantasyICL_backend.service;

import com.cricMaster.fantasyICL_backend.dto.*;
import com.cricMaster.fantasyICL_backend.model.Player;
import com.cricMaster.fantasyICL_backend.model.Team;
import com.cricMaster.fantasyICL_backend.model.TeamPlayer;
import com.cricMaster.fantasyICL_backend.model.Tournament;
import com.cricMaster.fantasyICL_backend.repository.PlayerRepository;
import com.cricMaster.fantasyICL_backend.repository.TeamPlayerRepository;
import com.cricMaster.fantasyICL_backend.repository.TeamRepository;
import com.cricMaster.fantasyICL_backend.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Service
@Transactional
public class AdminTournamentService {

    private final TournamentRepository tours;
    private final TeamRepository teams;
    private final TeamPlayerRepository roster;
    private final PlayerRepository players;

    public AdminTournamentService(
            TournamentRepository tours,
            TeamRepository teams,
            TeamPlayerRepository roster,
            PlayerRepository players
    ) {
        this.tours   = tours;
        this.teams   = teams;
        this.roster  = roster;
        this.players = players;
    }

    public TournamentResponse createTournament(TournamentRequest req) {
        if (req.endDate().isBefore(req.startDate())) {
            throw new ResponseStatusException(BAD_REQUEST, "endDate must be on or after startDate");
        }
        Tournament t = new Tournament();
        t.setName(req.name());
        t.setStartDate(req.startDate());
        t.setEndDate(req.endDate());
        t.setLocation(req.location());
        t.setBallType(req.ballType());
        tours.save(t);

        return toDto(t);
    }

    public TeamResponse createTeam(Long tournamentId, TeamRequest req) {
        Tournament t = tours.findById(tournamentId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tournament not found"));

        if (teams.existsByTournamentIdAndName(tournamentId, req.name())) {
            throw new ResponseStatusException(CONFLICT, "Team name already taken in this tournament");
        }

        Team team = new Team();
        team.setTournament(t);
        team.setName(req.name());
        team.setLogoUrl(req.logoUrl());
        team.setOwnerName(req.ownerName());
        teams.save(team);

        return toDto(team);
    }

    public void assignPlayer(Long tournamentId, Long teamId, Long playerId) {
        Team team = teams.findById(teamId)
                .filter(tm -> tm.getTournament().getId().equals(tournamentId))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Team not found in given tournament"));
        System.out.println("Team: " + team.getName());
        Player player = players.findById(playerId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Player not found"));
        System.out.println("Player details: " + player.getName());
        var key = new TeamPlayerId(teamId, playerId);
        System.out.println("Key: " + key);
        if (roster.existsById(key)) {
            throw new ResponseStatusException(BAD_REQUEST, "Player already in this team");
        } else {
            System.out.println("Adding player to team: " + team.getName());
        }

        TeamPlayer tp = new TeamPlayer();
        tp.setTeam(team);
        tp.setPlayer(player);
        roster.save(tp);
    }

    @Transactional(readOnly = true)
    public List<TeamResponse> listTeams(Long tournamentId) {
        if (!tours.existsById(tournamentId)) {
            throw new ResponseStatusException(NOT_FOUND, "Tournament not found");
        }
        return teams.findByTournamentId(tournamentId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private TournamentResponse toDto(Tournament t) {
        return new TournamentResponse(
                t.getId(), t.getName(),
                t.getStartDate(), t.getEndDate(),
                t.getLocation(), t.getBallType()
        );
    }

    private TeamResponse toDto(Team team) {
        var players = team.getRoster().stream()
                .map(r -> new PlayerMinDto(
                        r.getPlayer().getId(),
                        r.getPlayer().getName()))
                .toList();

        return new TeamResponse(
                team.getId(),
                team.getName(),
                team.getLogoUrl(),
                team.getOwnerName(),
                players
        );
    }
}
