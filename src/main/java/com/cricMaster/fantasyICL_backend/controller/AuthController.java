package com.cricMaster.fantasyICL_backend.controller;


import com.cricMaster.fantasyICL_backend.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cricMaster.fantasyICL_backend.service.AuthService;
import com.cricMaster.fantasyICL_backend.service.RefreshTokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthService         authService;
    @Autowired private RefreshTokenService refreshService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody SignupRequest req) {
        authService.registerUser(req);
        return ResponseEntity.ok(new ApiResponse(true, "User registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.authenticateUser(req));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(
            @RequestBody RefreshTokenRequest req) {
        return ResponseEntity.ok(authService.refreshToken(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(
            @RequestBody RefreshTokenRequest req) {
        refreshService.verifyAndRotate(req.getRefreshToken()); // revoke old
        return ResponseEntity.ok(new ApiResponse(true, "Logged out"));
    }
}