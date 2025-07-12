package com.cricMaster.fantasyICL_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class SignupRequest {

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8, max = 20, message = "Password must have atleast 8 characters")
    private String password;

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank @Size(min = 8, max = 20, message = "Password must have atleast 8 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 8, max = 20, message = "Password must have atleast 8 characters") String password) {
        this.password = password;
    }
}
