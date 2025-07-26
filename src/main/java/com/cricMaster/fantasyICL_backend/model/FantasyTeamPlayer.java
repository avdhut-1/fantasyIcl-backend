package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="fantasy_team_players")
public class FantasyTeamPlayer {

    @EmbeddedId
    private FantasyTeamPlayerId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("fantasyTeamId")
    @JoinColumn(name="fantasy_team_id", foreignKey=@ForeignKey(name="fk_ftp_team"))
    private FantasyTeam fantasyTeam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("playerId")
    @JoinColumn(name="player_id", foreignKey=@ForeignKey(name="fk_ftp_player"))
    private Player player;

    @CreatedDate
    @Column(name="selected_at", nullable=false,
            insertable=false, updatable=false) // assume DB default NOW()
    private Instant selectedAt;

    public FantasyTeamPlayer() {}

    public FantasyTeamPlayer(FantasyTeamPlayerId id, FantasyTeam fantasyTeam, Player player, Instant selectedAt) {
        this.id = id;
        this.fantasyTeam = fantasyTeam;
        this.player = player;
        this.selectedAt = selectedAt;
    }

    public FantasyTeamPlayerId getId() {
        return id;
    }

    public void setId(FantasyTeamPlayerId id) {
        this.id = id;
    }

    public FantasyTeam getFantasyTeam() {
        return fantasyTeam;
    }

    public void setFantasyTeam(FantasyTeam fantasyTeam) {
        this.fantasyTeam = fantasyTeam;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Instant getSelectedAt() {
        return selectedAt;
    }

    public void setSelectedAt(Instant selectedAt) {
        this.selectedAt = selectedAt;
    }
}

