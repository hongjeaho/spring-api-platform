/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.daos;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.generated.tables.JUserRole;
import org.jooq.generated.tables.pojos.UserRoleEntity;
import org.jooq.generated.tables.records.UserRoleRecord;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRoleDao extends DAOImpl<UserRoleRecord, UserRoleEntity, Long> {

    /**
     * Create a new UserRoleDao without any configuration
     */
    public UserRoleDao() {
        super(JUserRole.USER_ROLE, UserRoleEntity.class);
    }

    /**
     * Create a new UserRoleDao with an attached configuration
     */
    public UserRoleDao(Configuration configuration) {
        super(JUserRole.USER_ROLE, UserRoleEntity.class, configuration);
    }

    @Override
    public Long getId(UserRoleEntity object) {
        return object.getSeq();
    }

    /**
     * Fetch records that have <code>seq BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<UserRoleEntity> fetchRangeOfJSeq(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(JUserRole.USER_ROLE.SEQ, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>seq IN (values)</code>
     */
    public List<UserRoleEntity> fetchByJSeq(Long... values) {
        return fetch(JUserRole.USER_ROLE.SEQ, values);
    }

    /**
     * Fetch a unique record that has <code>seq = value</code>
     */
    public UserRoleEntity fetchOneByJSeq(Long value) {
        return fetchOne(JUserRole.USER_ROLE.SEQ, value);
    }

    /**
     * Fetch a unique record that has <code>seq = value</code>
     */
    public Optional<UserRoleEntity> fetchOptionalByJSeq(Long value) {
        return fetchOptional(JUserRole.USER_ROLE.SEQ, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<UserRoleEntity> fetchRangeOfJName(String lowerInclusive, String upperInclusive) {
        return fetchRange(JUserRole.USER_ROLE.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<UserRoleEntity> fetchByJName(String... values) {
        return fetch(JUserRole.USER_ROLE.NAME, values);
    }

    /**
     * Fetch a unique record that has <code>name = value</code>
     */
    public UserRoleEntity fetchOneByJName(String value) {
        return fetchOne(JUserRole.USER_ROLE.NAME, value);
    }

    /**
     * Fetch a unique record that has <code>name = value</code>
     */
    public Optional<UserRoleEntity> fetchOptionalByJName(String value) {
        return fetchOptional(JUserRole.USER_ROLE.NAME, value);
    }

    /**
     * Fetch records that have <code>created_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<UserRoleEntity> fetchRangeOfJCreatedBy(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(JUserRole.USER_ROLE.CREATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_by IN (values)</code>
     */
    public List<UserRoleEntity> fetchByJCreatedBy(Long... values) {
        return fetch(JUserRole.USER_ROLE.CREATED_BY, values);
    }

    /**
     * Fetch records that have <code>created_time BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<UserRoleEntity> fetchRangeOfJCreatedTime(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(JUserRole.USER_ROLE.CREATED_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created_time IN (values)</code>
     */
    public List<UserRoleEntity> fetchByJCreatedTime(LocalDateTime... values) {
        return fetch(JUserRole.USER_ROLE.CREATED_TIME, values);
    }

    /**
     * Fetch records that have <code>updated_by BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<UserRoleEntity> fetchRangeOfJUpdatedBy(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(JUserRole.USER_ROLE.UPDATED_BY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_by IN (values)</code>
     */
    public List<UserRoleEntity> fetchByJUpdatedBy(Long... values) {
        return fetch(JUserRole.USER_ROLE.UPDATED_BY, values);
    }

    /**
     * Fetch records that have <code>updated_time BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<UserRoleEntity> fetchRangeOfJUpdatedTime(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(JUserRole.USER_ROLE.UPDATED_TIME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated_time IN (values)</code>
     */
    public List<UserRoleEntity> fetchByJUpdatedTime(LocalDateTime... values) {
        return fetch(JUserRole.USER_ROLE.UPDATED_TIME, values);
    }
}
