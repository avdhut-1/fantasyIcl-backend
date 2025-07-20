package com.cricMaster.fantasyICL_backend.repository;

import com.cricMaster.fantasyICL_backend.model.FantasyTeamPlayer;
import com.cricMaster.fantasyICL_backend.model.FantasyTeamPlayerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FantasyTeamPlayerRepository
        extends JpaRepository<FantasyTeamPlayer, FantasyTeamPlayerId> {
}
