package com.cricMaster.fantasyICL_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    public BadRequestException(String msg) {
        super(HttpStatus.BAD_REQUEST, msg);
    }
}
