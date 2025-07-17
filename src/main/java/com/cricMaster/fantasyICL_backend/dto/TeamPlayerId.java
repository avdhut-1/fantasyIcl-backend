package com.cricMaster.fantasyICL_backend.dto;

import java.io.Serializable;
import java.util.Objects;

public class TeamPlayerId implements Serializable {

    private Long team;

    private Long player;

    public TeamPlayerId() {}

    public TeamPlayerId(Long team, Long player) {
        this.team = team;
        this.player = player;
    }

    public Long getPlayer() {
        return player;
    }

    public void setPlayer(Long player) {
        this.player = player;
    }

    public Long getTeam() {
        return team;
    }

    public void setTeam(Long team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamPlayerId that = (TeamPlayerId) o;
        return Objects.equals(team, that.team) && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, player);
    }
}
