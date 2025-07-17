package com.cricMaster.fantasyICL_backend.repository;

import com.cricMaster.fantasyICL_backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    List<Team> findByTournamentId(Long tournamentId);

    boolean existsByTournamentIdAndName(Long tournamentId, String name);
}
