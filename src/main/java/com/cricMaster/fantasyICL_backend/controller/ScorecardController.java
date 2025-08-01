package com.cricMaster.fantasyICL_backend.controller;

import com.cricMaster.fantasyICL_backend.dto.ScorecardRequest;
import com.cricMaster.fantasyICL_backend.service.ScorecardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/contests/{contestId}/scorecard")
public class ScorecardController {
    private final ScorecardService svc;

    public ScorecardController(ScorecardService svc) {
        this.svc = svc;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadScorecard(@PathVariable Long contestId,
                                @RequestBody ScorecardRequest req) {
        System.out.println("POST /api/admin/contests/" + contestId + "/scorecard");
        svc.ingest(contestId, req);
    }
}
