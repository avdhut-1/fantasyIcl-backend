package com.cricMaster.fantasyICL_backend.repository;

import com.cricMaster.fantasyICL_backend.model.FantasyTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FantasyTeamRepository extends JpaRepository<FantasyTeam,Long> {
    Optional<FantasyTeam> findByContestIdAndUserId(Long contestId, Long userId);
}
