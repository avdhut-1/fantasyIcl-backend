package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "fantasy_teams",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_user_contest",
                columnNames = {"contest_id","user_id"}
        )
)
public class FantasyTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // which contest
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="contest_id", nullable=false,
            foreignKey = @ForeignKey(name="fk_ft_contest"))
    private Contest contest;

    // which user
    @Column(name="user_id", nullable=false)
    private Long userId;

    @OneToMany(
            mappedBy = "fantasyTeam",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<FantasyTeamPlayer> players = new HashSet<>();

    @Column(name="created_at", nullable=false, updatable=false,
            insertable = false)   // assume DB default NOW()
    private Instant createdAt;

    @Column(name="updated_at", nullable=false,
            insertable = false)  // assume DB trigger
    private Instant updatedAt;

    public FantasyTeam() {}

    public FantasyTeam(Long id, Contest contest, Long userId, Set<FantasyTeamPlayer> players, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.contest = contest;
        this.userId = userId;
        this.players = players;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<FantasyTeamPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(Set<FantasyTeamPlayer> players) {
        this.players = players;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}

