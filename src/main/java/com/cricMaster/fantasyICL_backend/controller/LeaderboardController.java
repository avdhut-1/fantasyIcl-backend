package com.cricMaster.fantasyICL_backend.controller;

import com.cricMaster.fantasyICL_backend.dto.PlayerDto;
import com.cricMaster.fantasyICL_backend.service.LeaderboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final LeaderboardService svc;

    public LeaderboardController(LeaderboardService svc) {
        this.svc = svc;
    }

    @GetMapping("/batting")
    public ResponseEntity<List<PlayerDto>> batting(@RequestParam(required = false) String filter) {
        var list = svc.getBattingLeaderboard(filter);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/bowling")
    public ResponseEntity<List<PlayerDto>> bowling(@RequestParam(required = false) String filter) {
        var list = svc.getBowlingLeaderboard(filter);
        return ResponseEntity.ok(list);
    }
}
