package com.cricMaster.fantasyICL_backend.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String accessToken;

    public JwtAuthenticationResponse(String accessToken, String refreshToken, UserInfo user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    private String refreshToken;
    private UserInfo user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
