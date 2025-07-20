// src/main/java/com/cricMaster/fantasyICL_backend/controller/AdminContestController.java
package com.cricMaster.fantasyICL_backend.controller;

import com.cricMaster.fantasyICL_backend.dto.AvailablePlayersResponse;
import com.cricMaster.fantasyICL_backend.dto.ContestRequest;
import com.cricMaster.fantasyICL_backend.dto.ContestResponse;
import com.cricMaster.fantasyICL_backend.service.ContestService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/tournaments/{tournamentId}/contests")
@Slf4j
public class AdminContestController {
    private final ContestService contestService;

    public AdminContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContestResponse createContest(
            @PathVariable("tournamentId") Long tournamentId,
            @Valid @RequestBody ContestRequest req
    ) {
        return contestService.createContest(tournamentId, req);
    }

    @GetMapping
    public List<ContestResponse> list(
            @PathVariable("tournamentId") Long tournamentId
    ) {
        return contestService.list(tournamentId);
    }

    @GetMapping("/{contestId}")
    public ContestResponse getContestDetails(
            @PathVariable("tournamentId") Long tournamentId,
            @PathVariable("contestId") Long contestId
    ) {
        return contestService.getContestDetails(tournamentId, contestId);
    }

    @PutMapping("/{contestId}")
    public ContestResponse updateContest(
            @PathVariable("tournamentId") Long tournamentId,
            @PathVariable("contestId") Long contestId,
            @Valid @RequestBody ContestRequest req
    ) {
        return contestService.updateContestDetails(tournamentId, contestId, req);
    }

    @DeleteMapping("/{contestId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContest(
            @PathVariable("tournamentId") Long tournamentId,
            @PathVariable("contestId") Long contestId
    ) {
        contestService.deleteContestDetails(tournamentId, contestId);
    }

    @GetMapping("/{contestId}/players")
    public ResponseEntity<AvailablePlayersResponse> getAvailablePlayers(
            @PathVariable Long contestId
    ) {
        System.out.println("[ContestController] GET /api/contests/" + contestId + "/players");
        var resp = contestService.getAvailablePlayers(contestId);
        System.out.println("[ContestController]  returning response for contest " + contestId);
        return ResponseEntity.ok(resp);
    }
}

