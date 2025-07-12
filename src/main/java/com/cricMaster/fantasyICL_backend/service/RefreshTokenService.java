package com.cricMaster.fantasyICL_backend.service;

import com.cricMaster.fantasyICL_backend.exception.TokenRefreshException;
import com.cricMaster.fantasyICL_backend.model.RefreshToken;
import com.cricMaster.fantasyICL_backend.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cricMaster.fantasyICL_backend.repository.RefreshTokenRepository;
import com.cricMaster.fantasyICL_backend.repository.UserRepository;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${app.refreshTokenExpirationMs}")
    private long refreshTokenDurationMs;

    private final RefreshTokenRepository tokenRepo;
    private final UserRepository         userRepo;
    private final PasswordEncoder        passwordEncoder;

    public RefreshTokenService(
            RefreshTokenRepository tokenRepo,
            UserRepository userRepo,
            PasswordEncoder passwordEncoder
    ) {
        this.tokenRepo       = tokenRepo;
        this.userRepo        = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RefreshToken createRefreshToken(String username) {
        User u = userRepo.findByUsername(username)
                .orElseThrow(() -> new TokenRefreshException("User not found"));
        tokenRepo.deleteByUser(u);

        RefreshToken rt = new RefreshToken();
        rt.setUser(u);
        rt.setExpiresAt(Instant.now().plusMillis(refreshTokenDurationMs));

        String raw = rt.getId().toString();
        rt.setTokenHash(passwordEncoder.encode(raw));

        return tokenRepo.save(rt);
    }

    @Transactional
    public RefreshToken verifyAndRotate(String rawToken) {
        UUID id;
        try {
            id = UUID.fromString(rawToken);
        } catch (IllegalArgumentException ex) {
            throw new TokenRefreshException("Invalid refresh token format");
        }
        RefreshToken rt = tokenRepo.findById(id)
                .orElseThrow(() -> new TokenRefreshException("Refresh token not found"));
        if (rt.getExpiresAt().isBefore(Instant.now()) || rt.getRevoked()) {
            throw new TokenRefreshException("Expired or revoked token");
        }
        rt.setRevoked(true);
        tokenRepo.save(rt);
        System.out.println("Username: " + rt.getUser().getUsername());
        return createRefreshToken(rt.getUser().getUsername());
    }
}