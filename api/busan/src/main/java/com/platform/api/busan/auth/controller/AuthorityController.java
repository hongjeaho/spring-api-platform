package com.platform.api.busan.auth.controller;

import com.platform.api.busan.auth.service.AuthorityService;
import com.platform.common.web.util.JwtTokenUtil;
import com.platform.datasource.base.auth.AuthRequest;
import com.platform.datasource.base.auth.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authority API", description = "인증에 처리 API")
@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class AuthorityController {

    // token 만료 시간
    @Value("${jwt.expiration.period:86400000}") // 기본 하루 (1 * 24 * 60 * 60 * 1000)
    private long jwtExpirationPeriod;

    private final AuthorityService authorityService;

    @Operation(summary = "로그인 처리", description = "로그인 처리 후 인증된 정보를 내려준다.")
    @PostMapping("/login")
    public ResponseEntity<AuthUser> login(
            @RequestBody AuthRequest authRequest
    ) {
        final var user = authorityService.login(authRequest);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, JwtTokenUtil.makeAuthToken(user, jwtExpirationPeriod))
                .body(user);
    }
}
