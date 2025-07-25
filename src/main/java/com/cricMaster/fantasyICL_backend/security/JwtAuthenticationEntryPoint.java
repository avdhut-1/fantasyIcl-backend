package com.cricMaster.fantasyICL_backend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req,
                         HttpServletResponse res,
                         AuthenticationException ex)
            throws IOException, ServletException {
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
