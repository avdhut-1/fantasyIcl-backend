package com.cricMaster.fantasyICL_backend.repository;

import com.cricMaster.fantasyICL_backend.model.BowlingPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BowlingPerformanceRepository extends JpaRepository<BowlingPerformance, Long> {}
