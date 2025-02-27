package com.platform.api.sample.auth.service;


import com.platform.datasource.base.auth.AuthRequest;
import com.platform.datasource.base.auth.AuthUser;
import com.platform.datasource.base.config.database.PlatFormTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PlatFormTransactional(readOnly = true)
public class AuthorityService {
    private final AuthenticationManager authenticationManager;

    public AuthUser login(AuthRequest authRequest) {
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getId(),
                        authRequest.getPassword()));

        return (AuthUser) authentication.getPrincipal();
    }
}
