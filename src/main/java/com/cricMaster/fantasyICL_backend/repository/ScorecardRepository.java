package com.cricMaster.fantasyICL_backend.repository;

import com.cricMaster.fantasyICL_backend.model.Scorecard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScorecardRepository extends JpaRepository<Scorecard, Long> {}
