/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.generated.JStore;
import org.jooq.generated.Keys;
import org.jooq.generated.tables.JUser.UserPath;
import org.jooq.generated.tables.JUserRoleMapping.UserRoleMappingPath;
import org.jooq.generated.tables.records.UserRoleRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JUserRole extends TableImpl<UserRoleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>store.user_role</code>
     */
    public static final JUserRole USER_ROLE = new JUserRole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRoleRecord> getRecordType() {
        return UserRoleRecord.class;
    }

    /**
     * The column <code>store.user_role.seq</code>. Role 일련번호
     */
    public final TableField<UserRoleRecord, Long> SEQ = createField(DSL.name("seq"), SQLDataType.BIGINT.nullable(false).identity(true), this, "Role 일련번호");

    /**
     * The column <code>store.user_role.name</code>. Role명
     */
    public final TableField<UserRoleRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).nullable(false), this, "Role명");

    /**
     * The column <code>store.user_role.created_by</code>. 생성자
     */
    public final TableField<UserRoleRecord, Long> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.BIGINT.nullable(false), this, "생성자");

    /**
     * The column <code>store.user_role.created_time</code>. 생성일
     */
    public final TableField<UserRoleRecord, LocalDateTime> CREATED_TIME = createField(DSL.name("created_time"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "생성일");

    /**
     * The column <code>store.user_role.updated_by</code>. 수정자
     */
    public final TableField<UserRoleRecord, Long> UPDATED_BY = createField(DSL.name("updated_by"), SQLDataType.BIGINT.nullable(false), this, "수정자");

    /**
     * The column <code>store.user_role.updated_time</code>. 수정일
     */
    public final TableField<UserRoleRecord, LocalDateTime> UPDATED_TIME = createField(DSL.name("updated_time"), SQLDataType.LOCALDATETIME(0).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "수정일");

    private JUserRole(Name alias, Table<UserRoleRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private JUserRole(Name alias, Table<UserRoleRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>store.user_role</code> table reference
     */
    public JUserRole(String alias) {
        this(DSL.name(alias), USER_ROLE);
    }

    /**
     * Create an aliased <code>store.user_role</code> table reference
     */
    public JUserRole(Name alias) {
        this(alias, USER_ROLE);
    }

    /**
     * Create a <code>store.user_role</code> table reference
     */
    public JUserRole() {
        this(DSL.name("user_role"), null);
    }

    public <O extends Record> JUserRole(Table<O> path, ForeignKey<O, UserRoleRecord> childPath, InverseForeignKey<O, UserRoleRecord> parentPath) {
        super(path, childPath, parentPath, USER_ROLE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class UserRolePath extends JUserRole implements Path<UserRoleRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> UserRolePath(Table<O> path, ForeignKey<O, UserRoleRecord> childPath, InverseForeignKey<O, UserRoleRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private UserRolePath(Name alias, Table<UserRoleRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public UserRolePath as(String alias) {
            return new UserRolePath(DSL.name(alias), this);
        }

        @Override
        public UserRolePath as(Name alias) {
            return new UserRolePath(alias, this);
        }

        @Override
        public UserRolePath as(Table<?> alias) {
            return new UserRolePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : JStore.STORE;
    }

    @Override
    public Identity<UserRoleRecord, Long> getIdentity() {
        return (Identity<UserRoleRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<UserRoleRecord> getPrimaryKey() {
        return Keys.KEY_USER_ROLE_PRIMARY;
    }

    @Override
    public List<UniqueKey<UserRoleRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_USER_ROLE_UK_GROUP_NAME);
    }

    private transient UserRoleMappingPath _userRoleMapping;

    /**
     * Get the implicit to-many join path to the
     * <code>store.user_role_mapping</code> table
     */
    public UserRoleMappingPath userRoleMapping() {
        if (_userRoleMapping == null)
            _userRoleMapping = new UserRoleMappingPath(this, null, Keys.USER_ROLE_MAPPING_IBFK_2.getInverseKey());

        return _userRoleMapping;
    }

    /**
     * Get the implicit many-to-many join path to the <code>store.user</code>
     * table
     */
    public UserPath user() {
        return userRoleMapping().user();
    }

    @Override
    public JUserRole as(String alias) {
        return new JUserRole(DSL.name(alias), this);
    }

    @Override
    public JUserRole as(Name alias) {
        return new JUserRole(alias, this);
    }

    @Override
    public JUserRole as(Table<?> alias) {
        return new JUserRole(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public JUserRole rename(String name) {
        return new JUserRole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JUserRole rename(Name name) {
        return new JUserRole(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public JUserRole rename(Table<?> name) {
        return new JUserRole(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public JUserRole where(Condition condition) {
        return new JUserRole(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public JUserRole where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public JUserRole where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public JUserRole where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public JUserRole where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public JUserRole where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public JUserRole where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public JUserRole where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public JUserRole whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public JUserRole whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
