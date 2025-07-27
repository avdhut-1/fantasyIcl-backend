package com.cricMaster.fantasyICL_backend.repository;

import com.cricMaster.fantasyICL_backend.model.Innings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InningsRepository extends JpaRepository<Innings, Long> {}
