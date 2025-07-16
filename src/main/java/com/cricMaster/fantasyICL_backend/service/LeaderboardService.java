package com.cricMaster.fantasyICL_backend.service;

import com.cricMaster.fantasyICL_backend.dto.PlayerDto;
import com.cricMaster.fantasyICL_backend.repository.PlayerRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class LeaderboardService {

    private final PlayerRepository repo;

    public LeaderboardService(PlayerRepository repo) {
        this.repo = repo;
    }

    @Cacheable(cacheNames = "battingLeader", key = "#filter")
    public List<PlayerDto> getBattingLeaderboard(String filter) {
        if (!StringUtils.hasText(filter)) {
            filter = "Most Runs";
        }
        return switch (filter) {
            case "Most Runs"       -> repo.findTopBattingByRuns();
            case "Average"         -> repo.findTopBattingByAverage();
            case "Strike Rate"     -> repo.findTopBattingByStrikeRate();
            default -> throw new IllegalArgumentException("Unknown batting filter: " + filter);
        };
    }

    @Cacheable(cacheNames = "bowlingLeader", key = "#filter")
    public List<PlayerDto> getBowlingLeaderboard(String filter) {
        if (!StringUtils.hasText(filter)) {
            filter = "Most Wickets";
        }
        return switch (filter) {
            case "Most Wickets"    -> repo.findTopBowlingByWickets();
            case "Average"         -> repo.findTopBowlingByAverage();
            case "Economy"         -> repo.findTopBowlingByEconomy();
            case "Strike Rate"     -> repo.findTopBowlingByStrikeRate();
            default -> throw new IllegalArgumentException("Unknown bowling filter: " + filter);
        };
    }

    /** Call after any ETL / nightly load to clear the caches */
    @CacheEvict(cacheNames = { "battingLeader", "bowlingLeader" }, allEntries = true)
    public void evictAllLeaderboards() { }
}
