package com.cricMaster.fantasyICL_backend.service;

import com.cricMaster.fantasyICL_backend.dto.CreateFantasyTeamRequest;
import com.cricMaster.fantasyICL_backend.dto.FantasyTeamResponse;
import com.cricMaster.fantasyICL_backend.exception.BadRequestException;
import com.cricMaster.fantasyICL_backend.exception.NotFoundException;
import com.cricMaster.fantasyICL_backend.model.*;
import com.cricMaster.fantasyICL_backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FantasyTeamService {
    private final ContestRepository contests;
    private final TeamPlayerRepository rosterRepo;
    private final FantasyTeamRepository ftRepo;
    private final FantasyTeamPlayerRepository ftpRepo;
    private final PlayerRepository playerRepo;

    public FantasyTeamService(ContestRepository contests,
                              TeamPlayerRepository rosterRepo,
                              FantasyTeamRepository ftRepo,
                              FantasyTeamPlayerRepository ftpRepo,
                              PlayerRepository playerRepo) {
        this.contests  = contests;
        this.rosterRepo = rosterRepo;
        this.ftRepo     = ftRepo;
        this.ftpRepo   = ftpRepo;
        this.playerRepo = playerRepo;
    }

    @Transactional
    public FantasyTeamResponse createTeam(Long contestId,
                                          Long userId,
                                          CreateFantasyTeamRequest req) {
        System.out.println(">> createTeam: user=" + userId
                + " contest=" + contestId
                + " players=" + req.playerIds()
                + " captain=" + req.captainId()
                + " viceCaptain=" + req.viceCaptainId());

        // 1) ensure contest exists
        Contest contest = contests.findById(contestId)
                .orElseThrow(() -> new NotFoundException("Contest not found"));

        // 2) ensure user hasn't already created a team
        if (ftRepo.findByContestIdAndUserId(contestId, userId).isPresent()) {
            throw new BadRequestException("You've already joined this contest");
        }

        // 3) validate duplicates & size
        Set<Long> distinct = new HashSet<>(req.playerIds());
        if (distinct.size() != req.playerIds().size()) {
            throw new BadRequestException("Duplicate player in selection");
        }
        if (distinct.size() > 11) {
            throw new BadRequestException("Cannot select more than 11 players");
        }

        // 4) captain/vice-captain checks
        if (req.captainId().equals(req.viceCaptainId())) {
            throw new BadRequestException("Captain and vice-captain must be different");
        }
        if (!distinct.contains(req.captainId()) || !distinct.contains(req.viceCaptainId())) {
            throw new BadRequestException("Captain and vice-captain must be part of selected players");
        }

        // 5) fetch allowed player‐IDs for this contest (from both teams’ rosters)
        List<Long> allowed = rosterRepo
                .findByTeamId(contest.getTeamA().getId()).stream()
                .map(tp -> tp.getPlayer().getId())
                .collect(Collectors.toList());
        allowed.addAll(
                rosterRepo.findByTeamId(contest.getTeamB().getId()).stream()
                        .map(tp -> tp.getPlayer().getId())
                        .toList()
        );

        for (Long pid : distinct) {
            if (!allowed.contains(pid)) {
                throw new BadRequestException("Player " + pid + " is not in this contest");
            }
        }

        // 6) load all selected Player entities in one shot
        List<Player> selectedPlayers = playerRepo.findAllById(distinct);
        if (selectedPlayers.size() != distinct.size()) {
            // find missing IDs
            Set<Long> found = selectedPlayers.stream().map(Player::getId).collect(Collectors.toSet());
            distinct.removeAll(found);
            throw new BadRequestException("Players not found: " + distinct);
        }
        // map for easy lookup
        Map<Long, Player> playerMap = selectedPlayers.stream()
                .collect(Collectors.toMap(Player::getId, Function.identity()));

        Player captain = playerMap.get(req.captainId());
        Player viceCaptain = playerMap.get(req.viceCaptainId());

        // 7) persist FantasyTeam with captain/vice-captain
        FantasyTeam ft = new FantasyTeam();
        ft.setContest(contest);
        ft.setUserId(userId);
        ft.setCaptain(captain);
        ft.setViceCaptain(viceCaptain);
        ft = ftRepo.save(ft);

        // 8) persist team players
        for (Long pid : distinct) {
            FantasyTeamPlayer ftp = new FantasyTeamPlayer();
            ftp.setId(new FantasyTeamPlayerId(ft.getId(), pid));
            ftp.setFantasyTeam(ft);
            ftp.setPlayer(playerMap.get(pid)); // use the entity, not new Player(pid)
            ftpRepo.save(ftp);
        }

        System.out.println("<< createTeam success id=" + ft.getId());
        return new FantasyTeamResponse(
                ft.getId(),
                contestId,
                new ArrayList<>(distinct)
        );
    }}

