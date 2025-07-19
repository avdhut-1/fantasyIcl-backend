package com.cricMaster.fantasyICL_backend.repository;

import com.cricMaster.fantasyICL_backend.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ContestRepository extends JpaRepository<Contest,Long> {
    List<Contest> findByTournamentIdOrderByMatchDateAscMatchTimeAsc(Long tournamentId);
}