package com.platform.datasource.base.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.jooq.generated.tables.pojos.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "AuthUser", title = "인증 정보")
public class AuthUser implements UserDetails {
    @Schema(title = "사용자 일련번호")
    private Long seq;
    @Schema(title = "email")
    private String email;
    @Schema(title = "id")
    private String id;
    @Schema(title = "사용자 이름")
    private String name;
    @JsonIgnore
    private String password;

    @Schema(title = "권한정보")
    private Set<BasicAuthority> roles;

    public AuthUser(UserEntity userEntity, Set<BasicAuthority> roles) {
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        this.id = userEntity.getId();
        this.password = userEntity.getPassword();
        this.roles = roles;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return id;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles.isEmpty()) {
            return List.of();
        }

        return roles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getAuthority()))
                .collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}