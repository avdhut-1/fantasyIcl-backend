package com.cricMaster.fantasyICL_backend.service;

import com.cricMaster.fantasyICL_backend.dto.*;
import com.cricMaster.fantasyICL_backend.exception.BadRequestException;
import com.cricMaster.fantasyICL_backend.exception.NotFoundException;
import com.cricMaster.fantasyICL_backend.model.*;
import com.cricMaster.fantasyICL_backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ScorecardService {
    private final ContestRepository contestRepo;
    private final ScorecardRepository scorecardRepo;
    private final PlayerRepository playerRepository;

    public ScorecardService(ContestRepository contestRepo,
                            ScorecardRepository scorecardRepo, PlayerRepository playerRepository) {
        this.contestRepo = contestRepo;
        this.scorecardRepo = scorecardRepo;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void ingest(Long contestId, ScorecardRequest req) {
        System.out.println(">> ingesting scorecard for contest " + contestId);
        var contest = contestRepo.findById(contestId)
                .orElseThrow(() -> new BadRequestException("Contest not found: " + contestId));

        // prevent double-post
        if (scorecardRepo.existsById(contestId)) {
            throw new BadRequestException("Scorecard already uploaded for contest " + contestId);
        }

        Set<String> allNames = req.getInnings().stream()
                .flatMap(in -> Stream.concat(
                        in.getBatting().stream().map(b -> b.getPlayerName().toLowerCase()),
                        in.getBowling().stream().map(b -> b.getPlayerName().toLowerCase())
                ))
                .collect(Collectors.toSet());

        // map and save
        Scorecard sc = new Scorecard();
        sc.setContest(contest);
        sc.setToss(req.getSummary().getToss());
        sc.setScoreSummary(req.getSummary().getScoreSummary());
        sc.setResult(req.getSummary().getResult());

        List<Player> players = playerRepository.findByNameInIgnoreCase(allNames);
        Map<String, Player> byLowerName = players.stream()
                .collect(Collectors.toMap(p -> p.getName().toLowerCase(), Function.identity()));

        for (InningsDto dto : req.getInnings()) {
            Innings inn = new Innings();
            inn.setScorecard(sc);
            inn.setInningsNumber(dto.getInningsNumber());
            // assume team exists
            Team team = new Team();
            team.setId(dto.getBattingTeamId());
            inn.setBattingTeam(team);
            inn.setCaptainName(dto.getCaptainName());

            dto.getBatting().forEach(bd -> {
                Player p = byLowerName.get(bd.getPlayerName().toLowerCase());
                if (p == null) throw new NotFoundException("Player not found: " + bd.getPlayerName());
                var bp = new BattingPerformance();
                bp.setInnings(inn);
                bp.setPlayer(p);
                bp.setRuns(bd.getRuns());
                bp.setBalls(bd.getBalls());
                bp.setFours(bd.getFours());
                bp.setSixes(bd.getSixes());
                bp.setStrikeRate(bd.getStrikeRate());
                inn.getBatting().add(bp);
            });
            dto.getBowling().forEach(bd -> {
                Player p = byLowerName.get(bd.getPlayerName().toLowerCase());
                if (p == null) throw new NotFoundException("Player not found: " + bd.getPlayerName());
                var wp = new BowlingPerformance();
                wp.setInnings(inn);
                wp.setPlayer(p);
                wp.setOvers(bd.getOvers());
                wp.setRunsConceded(bd.getRunsConceded());
                wp.setWickets(bd.getWickets());
                wp.setEconomy(bd.getEconomy());
                inn.getBowling().add(wp);
            });

            sc.getInnings().add(inn);
        }

        scorecardRepo.save(sc);
        System.out.println("<< scorecard ingested.");
    }
}
