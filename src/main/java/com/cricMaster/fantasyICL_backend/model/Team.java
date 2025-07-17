package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="tournament_id")
    private Tournament tournament;

    @Column(nullable=false)
    private String name;

    @Column(name="logo_url")
    private String logoUrl;

    @Column(name="owner_name", nullable=false)
    private String ownerName;

    @OneToMany(mappedBy="team", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<TeamPlayer> roster = new HashSet<>();

    public Team() {}

    public Team(Long id, Tournament tournament, String name, String logoUrl, String ownerName, Set<TeamPlayer> roster) {
        this.id = id;
        this.tournament = tournament;
        this.name = name;
        this.logoUrl = logoUrl;
        this.ownerName = ownerName;
        this.roster = roster;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Set<TeamPlayer> getRoster() {
        return roster;
    }

    public void setRoster(Set<TeamPlayer> roster) {
        this.roster = roster;
    }
}

