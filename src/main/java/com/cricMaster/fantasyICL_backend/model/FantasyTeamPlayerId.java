package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@Embeddable
public class FantasyTeamPlayerId implements Serializable {
    private Long fantasyTeamId;
    private Long playerId;

    public FantasyTeamPlayerId() {}

    public FantasyTeamPlayerId(Long fantasyTeamId, Long playerId) {
        this.fantasyTeamId = fantasyTeamId;
        this.playerId = playerId;
    }

    public Long getFantasyTeamId() {
        return fantasyTeamId;
    }

    public void setFantasyTeamId(Long fantasyTeamId) {
        this.fantasyTeamId = fantasyTeamId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}