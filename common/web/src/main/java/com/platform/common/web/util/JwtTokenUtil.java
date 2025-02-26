package com.platform.common.web.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.platform.common.web.auth.VerifyResult;
import com.platform.datasource.base.auth.AuthUser;

import java.util.Date;

public class JwtTokenUtil {
    private static final String JWT_ISSUER = "ltis.go.kr";
    private static final String SECRET = "kr.go.ltis.common.core.util.JWTUtil";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    // 토큰 생성
    public static String makeAuthToken(final AuthUser user) {
        long jwtExpirationPeriod = 24 * 60 * 60 * 1000; // 하루
        return makeAuthToken(user, jwtExpirationPeriod);
    }

    // 토큰을 생성한다.
    public static String makeAuthToken(final AuthUser user, long jwtExpirationPeriod) {
        var currentTimeMillis = System.currentTimeMillis();
        return JWT.create()
                .withIssuer(JWT_ISSUER)
                .withSubject(user.getUsername())
                .withIssuedAt(new Date(currentTimeMillis))
                .withExpiresAt(new Date(currentTimeMillis + jwtExpirationPeriod))
                .sign(ALGORITHM);
    }

    // 토큰 정보를 조회 한다.
    public static VerifyResult verify(final String token) {
        try {
            DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
            return VerifyResult.builder().success(true)
                    .email(verify.getSubject()).build();
        } catch (JWTVerificationException ex) {
            return decode(token);
        }
    }

    // 만료 시간을 조회 한다.
    public static Date getExpirationDate(String token) {
        return JWT.require(ALGORITHM).build().verify(token).getExpiresAt();
    }

    private static VerifyResult decode(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            return VerifyResult.builder().success(false)
                    .email(decode.getSubject())
                    .message("토큰이 만료 되었습니다.").build();
        } catch (Exception ex) {
            return VerifyResult.builder().success(false)
                    .message(ex.getMessage()).build();
        }
    }
}
