package com.cricMaster.fantasyICL_backend.repository;

import com.cricMaster.fantasyICL_backend.model.RefreshToken;
import com.cricMaster.fantasyICL_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findById(UUID id);
    void deleteByUser(User user);
}
