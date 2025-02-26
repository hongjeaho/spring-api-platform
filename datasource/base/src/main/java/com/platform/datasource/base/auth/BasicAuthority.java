package com.platform.datasource.base.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "BasicAuthority", title = "권한 정보")
public class BasicAuthority implements GrantedAuthority {

    @Schema(title = "사용자 일련번호")
    private long userSeq;
    @Schema(title = "사용자 권한")
    private String role;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return role;
    }
}
