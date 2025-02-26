package com.platform.common.web.util;

import com.platform.common.web.auth.VerifyResult;
import com.platform.datasource.base.auth.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class JwtTokenUtilTest {

    @DisplayName("JWT 토큰 성생")
    @Test
    public void createJwtToken() {
        AuthUser authUser = new AuthUser();
        authUser.setId("admin");

        String token = JwtTokenUtil.makeAuthToken(authUser);
        log.info(token);
    }

    @DisplayName("JWT 토큰 인증 성공")
    @Test
    public void verifyJwtToken() {
        AuthUser authUser = new AuthUser();
        authUser.setId("admin");

        String token = JwtTokenUtil.makeAuthToken(authUser);
        VerifyResult verify = JwtTokenUtil.verify(token);

        assertTrue(verify.isSuccess());
    }

    @DisplayName("JWT 토큰 인증 실패")
    @Test
    public void verifyJwtTokenFail() throws InterruptedException {
        AuthUser authUser = new AuthUser();
        authUser.setId("admin");

        String token = JwtTokenUtil.makeAuthToken(authUser, 1);
        Thread.sleep(1000);
        VerifyResult verify = JwtTokenUtil.verify(token);

        assertFalse(verify.isSuccess());
    }
}
