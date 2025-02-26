package com.platform.common.web.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.platform.common.web.dto.BaseApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<BaseApiResponse<Void>> handleOrderInValidException(
            final HttpServletRequest request,
            final JWTDecodeException ex) {

        log.error("JWTDecodeException {}\n", request.getRequestURI(), ex);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseApiResponse.ofError(ex));
    }
}
