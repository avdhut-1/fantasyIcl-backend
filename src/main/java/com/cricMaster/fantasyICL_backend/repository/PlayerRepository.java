package com.cricMaster.fantasyICL_backend.repository;

import com.cricMaster.fantasyICL_backend.dto.PlayerDto;
import com.cricMaster.fantasyICL_backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // --- Batting queries ---
    @Query("""
    SELECT new com.cricMaster.fantasyICL_backend.dto.PlayerDto(
      p.id, p.name, p.creditScore,
      bs.matches, bs.runs, NULL,
      bs.average, NULL,
      bs.strikeRate
    )
    FROM Player p
    JOIN p.battingStats bs
    ORDER BY bs.runs DESC
    """)
    List<PlayerDto> findTopBattingByRuns();

    @Query("""
    SELECT new com.cricMaster.fantasyICL_backend.dto.PlayerDto(
      p.id, p.name, p.creditScore,
      bs.matches, bs.runs, NULL,
      bs.average, NULL,
      bs.strikeRate
    )
    FROM Player p
    JOIN p.battingStats bs
    ORDER BY bs.average DESC
    """)
    List<PlayerDto> findTopBattingByAverage();

    @Query("""
    SELECT new com.cricMaster.fantasyICL_backend.dto.PlayerDto(
      p.id, p.name, p.creditScore,
      bs.matches, bs.runs, NULL,
      bs.average, NULL,
      bs.strikeRate
    )
    FROM Player p
    JOIN p.battingStats bs
    ORDER BY bs.strikeRate DESC
    """)
    List<PlayerDto> findTopBattingByStrikeRate();

    // --- Bowling queries ---
    @Query("""
    SELECT new com.cricMaster.fantasyICL_backend.dto.PlayerDto(
      p.id, p.name, p.creditScore,
      bw.matches, NULL, bw.wickets,
      bw.average, bw.economy,
      bw.strikeRate
    )
    FROM Player p
    JOIN p.bowlingStats bw
    ORDER BY bw.wickets DESC
    """)
    List<PlayerDto> findTopBowlingByWickets();

    @Query("""
    SELECT new com.cricMaster.fantasyICL_backend.dto.PlayerDto(
      p.id, p.name, p.creditScore,
      bw.matches, NULL, bw.wickets,
      bw.average, bw.economy,
      bw.strikeRate
    )
    FROM Player p
    JOIN p.bowlingStats bw
    ORDER BY bw.average ASC
    """)
    List<PlayerDto> findTopBowlingByAverage();

    @Query("""
    SELECT new com.cricMaster.fantasyICL_backend.dto.PlayerDto(
      p.id, p.name, p.creditScore,
      bw.matches, NULL, bw.wickets,
      bw.average, bw.economy,
      bw.strikeRate
    )
    FROM Player p
    JOIN p.bowlingStats bw
    ORDER BY bw.economy ASC
    """)
    List<PlayerDto> findTopBowlingByEconomy();

    @Query("""
    SELECT new com.cricMaster.fantasyICL_backend.dto.PlayerDto(
      p.id, p.name, p.creditScore,
      bw.matches, NULL, bw.wickets,
      bw.average, bw.economy,
      bw.strikeRate
    )
    FROM Player p
    JOIN p.bowlingStats bw
    ORDER BY bw.strikeRate ASC
    """)
    List<PlayerDto> findTopBowlingByStrikeRate();
}
