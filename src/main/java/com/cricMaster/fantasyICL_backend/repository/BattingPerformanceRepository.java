package com.cricMaster.fantasyICL_backend.repository;

import com.cricMaster.fantasyICL_backend.model.BattingPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattingPerformanceRepository extends JpaRepository<BattingPerformance, Long> {}
