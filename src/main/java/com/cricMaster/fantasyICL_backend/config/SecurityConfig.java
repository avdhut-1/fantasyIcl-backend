package com.cricMaster.fantasyICL_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import jakarta.servlet.Filter;
import com.cricMaster.fantasyICL_backend.security.JwtAuthenticationFilter;
import com.cricMaster.fantasyICL_backend.service.AuthService;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private final AuthenticationEntryPoint   unauthorizedHandler;
    private final AuthService                authService;
    private final JwtAuthenticationFilter    jwtAuthenticationFilter;
    private final PasswordEncoder            passwordEncoder;

    public SecurityConfig(AuthenticationEntryPoint unauthorizedHandler, AuthService authService, JwtAuthenticationFilter jwtAuthenticationFilter, PasswordEncoder passwordEncoder) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.authService = authService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return authService;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthProvider() {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(userDetailsService());
        p.setPasswordEncoder(passwordEncoder);
        return p;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration cfg
    ) throws Exception {
        return cfg.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            org.springframework.security.config.annotation.web.builders.HttpSecurity http
    ) throws Exception {

        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(unauthorizedHandler))
                .authenticationProvider(daoAuthProvider())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore((Filter) jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}