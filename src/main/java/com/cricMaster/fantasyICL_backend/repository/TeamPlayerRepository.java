package com.cricMaster.fantasyICL_backend.repository;

import com.cricMaster.fantasyICL_backend.dto.TeamPlayerId;
import com.cricMaster.fantasyICL_backend.model.TeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPlayerRepository extends JpaRepository<TeamPlayer, TeamPlayerId> {
    List<TeamPlayer> findByTeamId(Long teamId);
}
