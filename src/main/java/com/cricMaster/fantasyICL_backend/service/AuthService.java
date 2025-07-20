package com.cricMaster.fantasyICL_backend.service;

import com.cricMaster.fantasyICL_backend.dto.*;
import com.cricMaster.fantasyICL_backend.model.User;
import com.cricMaster.fantasyICL_backend.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cricMaster.fantasyICL_backend.repository.UserRepository;
import com.cricMaster.fantasyICL_backend.security.JwtTokenProvider;

@Service
public class AuthService implements UserDetailsService {
    @Autowired private AuthenticationConfiguration authConfig;
    @Autowired private UserRepository       userRepo;
    @Autowired private PasswordEncoder     passwordEncoder;
    @Autowired private JwtTokenProvider    jwtProvider;
    @Autowired private RefreshTokenService refreshService;

    public void registerUser(SignupRequest req) {
        if (userRepo.existsByUsername(req.getUsername()))
            throw new RuntimeException("Username taken");
        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepo.save(u);
    }

    public JwtAuthenticationResponse authenticateUser(LoginRequest req) {
        authenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(), req.getPassword()
                )
        );
        String accessToken = jwtProvider.generateToken(req.getUsername());
        var rt = refreshService.createRefreshToken(req.getUsername());
        var u = userRepo.findByUsername(req.getUsername()).orElseThrow();
        return new JwtAuthenticationResponse(
                accessToken,
                rt.getId().toString(),
                new UserInfo(u.getId(), u.getUsername())
        );
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest req) {
        var rt = refreshService.verifyAndRotate(req.getRefreshToken());
        String accessToken = jwtProvider.generateToken(rt.getUser().getUsername());
        return new JwtAuthenticationResponse(
                accessToken,
                rt.getId().toString(),
                new UserInfo(rt.getUser().getId(), rt.getUser().getUsername())
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user: " + username));
        return UserPrincipal.create(user);
    }

       private AuthenticationManager authenticationManager() {
              try {
                    return authConfig.getAuthenticationManager();
                  } catch (Exception ex) {
                     throw new BadCredentialsException("Unable to authenticate", ex);
                  }
           }
}