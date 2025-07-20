package com.cricMaster.fantasyICL_backend.service;

import com.cricMaster.fantasyICL_backend.dto.CreateFantasyTeamRequest;
import com.cricMaster.fantasyICL_backend.dto.FantasyTeamResponse;
import com.cricMaster.fantasyICL_backend.exception.BadRequestException;
import com.cricMaster.fantasyICL_backend.exception.NotFoundException;
import com.cricMaster.fantasyICL_backend.model.*;
import com.cricMaster.fantasyICL_backend.repository.ContestRepository;
import com.cricMaster.fantasyICL_backend.repository.FantasyTeamPlayerRepository;
import com.cricMaster.fantasyICL_backend.repository.FantasyTeamRepository;
import com.cricMaster.fantasyICL_backend.repository.TeamPlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FantasyTeamService {
    private final ContestRepository contests;
    private final TeamPlayerRepository rosterRepo;
    private final FantasyTeamRepository ftRepo;
    private final FantasyTeamPlayerRepository ftpRepo;

    public FantasyTeamService(ContestRepository contests,
                              TeamPlayerRepository rosterRepo,
                              FantasyTeamRepository ftRepo,
                              FantasyTeamPlayerRepository ftpRepo) {
        this.contests  = contests;
        this.rosterRepo = rosterRepo;
        this.ftRepo     = ftRepo;
        this.ftpRepo   = ftpRepo;
    }

    @Transactional
    public FantasyTeamResponse createTeam(Long contestId,
                                          Long userId,
                                          CreateFantasyTeamRequest req) {
        System.out.println(">> createTeam: user=" + userId
                + " contest=" + contestId
                + " players=" + req.playerIds());

        // 1) ensure contest exists
        Contest contest = contests.findById(contestId)
                .orElseThrow(() -> new NotFoundException("Contest not found"));

        // 2) ensure user hasn't already created a team
        if (ftRepo.findByContestIdAndUserId(contestId, userId).isPresent()) {
            throw new BadRequestException("You've already joined this contest");
        }

        // 3) validate no duplicates & count
        Set<Long> distinct = new HashSet<>(req.playerIds());
        if (distinct.size() != req.playerIds().size()) {
            throw new BadRequestException("Duplicate player in selection");
        }
        if (distinct.size() > 11) {
            throw new BadRequestException("Cannot select more than 11 players");
        }

        // 4) fetch allowed player‐IDs for this contest from both teams’ rosters
        List<Long> allowed = rosterRepo
                .findByTeamId(contest.getTeamA().getId()).stream()
                .map(tp->tp.getPlayer().getId())
                .collect(Collectors.toList());
        allowed.addAll(
                rosterRepo.findByTeamId(contest.getTeamB().getId()).stream()
                        .map(tp->tp.getPlayer().getId())
                        .toList()
        );

        // 5) ensure every chosen ID is allowed
        for (Long pid : distinct) {
            if (!allowed.contains(pid)) {
                throw new BadRequestException("Player " + pid
                        + " is not in this contest");
            }
        }

        // 6) persist FantasyTeam + FantasyTeamPlayer rows
        FantasyTeam ft = new FantasyTeam();
        ft.setContest(contest);
        ft.setUserId(userId);
        ft = ftRepo.save(ft);

        for (Long pid : distinct) {
            FantasyTeamPlayer ftp = new FantasyTeamPlayer();
            ftp.setId(new FantasyTeamPlayerId(ft.getId(), pid));
            ftp.setFantasyTeam(ft);
            ftp.setPlayer(new Player(pid));
            ftpRepo.save(ftp);
        }

        System.out.println("<< createTeam success id=" + ft.getId());
        return new FantasyTeamResponse(
                ft.getId(),
                contestId,
                new ArrayList<>(distinct)
        );
    }
}
