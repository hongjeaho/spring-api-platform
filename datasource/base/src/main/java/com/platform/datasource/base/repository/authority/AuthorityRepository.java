package com.platform.datasource.base.repository.authority;


import com.platform.datasource.base.auth.AuthUser;
import com.platform.datasource.base.auth.BasicAuthority;
import com.platform.datasource.base.config.database.PlatFormTransactional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.generated.tables.JUser;
import org.jooq.generated.tables.JUserRole;
import org.jooq.generated.tables.JUserRoleMapping;
import org.jooq.generated.tables.pojos.UserEntity;
import org.jooq.impl.DSL;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@PlatFormTransactional
@Repository
@RequiredArgsConstructor
public class AuthorityRepository {

    private final DSLContext dslContext;
    private final JUser User = JUser.USER;
    private final JUserRole UserRole = JUserRole.USER_ROLE;
    private final JUserRoleMapping UserRoleMapping = JUserRoleMapping.USER_ROLE_MAPPING;

    public AuthUser findAuthorById(String id) {
        Map<UserEntity, List<BasicAuthority>> userMap = dslContext.select(
                DSL.row(User.SEQ, User.EMAIL, User.ID, User.PASSWORD, User.NAME).as("user"),
                DSL.row(UserRoleMapping.USER_SEQ, UserRole.NAME.as("role")).as("roles")
            )
            .from(User)
                .join(UserRoleMapping)
                    .on(User.SEQ.eq(UserRoleMapping.USER_SEQ))
                .join(UserRole)
                    .on(UserRole.SEQ.eq(UserRoleMapping.ROLE_SEQ))
            .where(User.ID.eq(id))
            .fetchGroups(
                record -> record.get("user", UserEntity.class),
                record -> record.get("roles", BasicAuthority.class)
            );

        return userMap.entrySet().stream()
            .map(entry -> {
                Set<BasicAuthority> rolesSet = new HashSet<>(entry.getValue());  // List -> Set 변환
                return new AuthUser(entry.getKey(), rolesSet);  // AuthMember 생성
            })
            .findFirst()
            .orElseThrow(() -> new UsernameNotFoundException("id[" + id + "] not found."));
    }
}
