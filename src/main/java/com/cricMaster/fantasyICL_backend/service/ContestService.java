package com.cricMaster.fantasyICL_backend.service;

import com.cricMaster.fantasyICL_backend.dto.*;
import com.cricMaster.fantasyICL_backend.exception.BadRequestException;
import com.cricMaster.fantasyICL_backend.exception.NotFoundException;
import com.cricMaster.fantasyICL_backend.model.Contest;
import com.cricMaster.fantasyICL_backend.model.Player;
import com.cricMaster.fantasyICL_backend.model.Team;
import com.cricMaster.fantasyICL_backend.model.Tournament;
import com.cricMaster.fantasyICL_backend.repository.ContestRepository;
import com.cricMaster.fantasyICL_backend.repository.TeamRepository;
import com.cricMaster.fantasyICL_backend.repository.TournamentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContestService {
    private final ContestRepository contests;
    private final TournamentRepository tours;
    private final TeamRepository teams;

    public ContestService(ContestRepository contests,
                          TournamentRepository tours,
                          TeamRepository teams) {
        this.contests = contests;
        this.tours    = tours;
        this.teams    = teams;
    }

    private ContestResponse toDto(Contest c) {
        return new ContestResponse(
                c.getId(),
                c.getTournament().getId(),
                c.getTeamA().getId(),
                c.getTeamA().getName(),
                c.getTeamB().getId(),
                c.getTeamB().getName(),
                c.getMatchDate(),
                c.getMatchTime(),
                c.getVenue()
        );
    }

    @Transactional
    public ContestResponse createContest(Long tournamentId, ContestRequest req) {
        System.out.println("Creating contest for tournament: " + tournamentId + " " + req.teamAId() + " Vs " + req.teamBId());

        if (req.teamAId().equals(req.teamBId())) {
            throw new BadRequestException("A team cannot play itself");
        }

        Tournament t = tours.findById(tournamentId)
                .orElseThrow(() -> new NotFoundException("Tournament not found: " + tournamentId));

        Team a = teams.findById(req.teamAId())
                .filter(tm -> tm.getTournament().getId().equals(tournamentId))
                .orElseThrow(() -> new BadRequestException("TeamA not in this tournament"));

        Team b = teams.findById(req.teamBId())
                .filter(tm -> tm.getTournament().getId().equals(tournamentId))
                .orElseThrow(() -> new BadRequestException("TeamB not in this tournament"));

        Contest c = new Contest();
        c.setTournament(t);
        c.setTeamA(a);
        c.setTeamB(b);
        c.setMatchDate(req.matchDate());
        c.setMatchTime(req.matchTime());
        c.setVenue(req.venue());

        return toDto(contests.save(c));
    }

    @Transactional(readOnly=true)
    public ContestResponse getContestDetails(Long tournamentId, Long contestId) {
        Contest c = contests.findById(contestId)
                .filter(ct -> ct.getTournament().getId().equals(tournamentId))
                .orElseThrow(() -> new NotFoundException("Contest not found"));
        return toDto(c);
    }

    @Transactional(readOnly=true)
    public List<ContestResponse> list(Long tournamentId) {
        return contests.findByTournamentIdOrderByMatchDateAscMatchTimeAsc(tournamentId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ContestResponse updateContestDetails(Long tournamentId,
                                  Long contestId,
                                  ContestRequest req) {

        Contest existing = contests.findById(contestId)
                .filter(ct -> ct.getTournament().getId().equals(tournamentId))
                .orElseThrow(() -> new NotFoundException("Contest not found"));

        if (req.teamAId().equals(req.teamBId())) {
            throw new BadRequestException("A team cannot play itself");
        }

        Team a = teams.findById(req.teamAId())
                .filter(tm -> tm.getTournament().getId().equals(tournamentId))
                .orElseThrow(() -> new BadRequestException("TeamA not in this tournament"));

        Team b = teams.findById(req.teamBId())
                .filter(tm -> tm.getTournament().getId().equals(tournamentId))
                .orElseThrow(() -> new BadRequestException("TeamB not in this tournament"));

        existing.setTeamA(a);
        existing.setTeamB(b);
        existing.setMatchDate(req.matchDate());
        existing.setMatchTime(req.matchTime());
        existing.setVenue(req.venue());

        return toDto(contests.save(existing));
    }

    @Transactional
    public void deleteContestDetails(Long tournamentId, Long contestId) {
        Contest c = contests.findById(contestId)
                .filter(ct -> ct.getTournament().getId().equals(tournamentId))
                .orElseThrow(() -> new NotFoundException("Contest not found"));
        contests.delete(c);
    }

    @Transactional(readOnly = true)
    public AvailablePlayersResponse getAvailablePlayers(Long contestId) {
        System.out.println("[ContestService] getAvailablePlayers() → contestId=" + contestId);
        Contest contest = contests.findById(contestId)
                .orElseThrow(() -> new NotFoundException("Contest not found: " + contestId));

        Team a = contest.getTeamA();
        Team b = contest.getTeamB();
        System.out.println("[ContestService]  TeamA=" + a.getId() + "  TeamB=" + b.getId());

        var playersA = a.getRoster().stream()
                .map(rp -> toDto(rp.getPlayer()))
                .collect(Collectors.toList());

        var playersB = b.getRoster().stream()
                .map(rp -> toDto(rp.getPlayer()))
                .collect(Collectors.toList());

        System.out.println("[ContestService]  Found " + playersA.size() +
                " for A, " + playersB.size() + " for B");

        var tA = new SimpleTeamDto(a.getId(), a.getName(), playersA);
        var tB = new SimpleTeamDto(b.getId(), b.getName(), playersB);
        return new AvailablePlayersResponse(contestId, tA, tB);
    }

    private PlayerSelectionDto toDto(Player p) {
        return new PlayerSelectionDto(
                p.getId(),
                p.getName(),
                "avatarURL",
                p.getCreditScore()
        );
    }

}
