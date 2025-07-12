package com.cricMaster.fantasyICL_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserInfo {

    private Long id;

    public UserInfo(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
