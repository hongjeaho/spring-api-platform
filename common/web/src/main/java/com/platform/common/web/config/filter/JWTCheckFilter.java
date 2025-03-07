package com.platform.common.web.config.filter;

import com.platform.common.web.auth.VerifyResult;
import com.platform.common.web.util.JwtTokenUtil;
import com.platform.datasource.base.auth.AuthUser;
import com.platform.datasource.base.repository.authority.AuthorityRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTCheckFilter extends OncePerRequestFilter {
    private static final String SPACE = " ";
    private final AuthorityRepository authorityRepository;

    // token 만료 시간
    @Value("${jwt.expiration.period:86400000}") // 기본 하루 (1 * 24 * 60 * 60 * 1000)
    private long jwtExpirationPeriod;

    // token 만료 남은 시간
    @Value("${jwt.expiration.renew-before:3600000}") // 한시간 (1 * 60 * 60 * 1000)
    private long jwtTokenRenewBefore;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final FilterChain filterChain) throws ServletException, IOException {

        final var headerAuthorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (headerAuthorization == null || !headerAuthorization.startsWith("Bearer")
                || headerAuthorization.split(SPACE).length != 2) {
            filterChain.doFilter(request, response);
            return;
        }

        final var token = headerAuthorization.split(SPACE)[1].trim();
        VerifyResult verify = JwtTokenUtil.verify(token);

        if (!verify.isSuccess()) {
            log.error(verify.getMessage());
            response.setHeader("X-Token-Expired", "true");
            filterChain.doFilter(request, response);
            return;
        }

        final var user = authorityRepository.findAuthorById(verify.getEmail());
        if (user == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final var authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );

        // 만료 시간이 얼마 남지 않았다면 시간을 연장 한다.
        checkAndRenewAccessToken(response, user, token);

        // 인증 정보를 생성하여 SecurityContext에 등록한다.
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private void checkAndRenewAccessToken(
            final HttpServletResponse response, final AuthUser user, final String token
    ) {
        final var tokenExpirationDate = JwtTokenUtil.getExpirationDate(token);
        final var comparingTime = tokenExpirationDate.getTime() - jwtTokenRenewBefore;
        if (new Date().after(new Date(comparingTime))) {
            response.setHeader(HttpHeaders.AUTHORIZATION, JwtTokenUtil.makeAuthToken(user, jwtExpirationPeriod));
        }
    }
}
