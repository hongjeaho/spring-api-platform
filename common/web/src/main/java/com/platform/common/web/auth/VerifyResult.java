package com.platform.common.web.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyResult {
    private boolean success;
    private String email;
    private String message;
}
