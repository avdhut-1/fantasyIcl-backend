package com.cricMaster.fantasyICL_backend.model;

import com.cricMaster.fantasyICL_backend.dto.TeamPlayerId;
import jakarta.persistence.*;

@Entity
@Table(name = "team_players")
@IdClass(TeamPlayerId.class)
public class TeamPlayer {
    @Id
    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    @Id
    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;

    public TeamPlayer() {}

    public TeamPlayer(Team team, Player player) {
        this.team = team;
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

