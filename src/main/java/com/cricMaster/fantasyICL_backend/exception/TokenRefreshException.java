package com.cricMaster.fantasyICL_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenRefreshException extends RuntimeException {
    public TokenRefreshException(String msg) {
        super(msg);
    }
}
