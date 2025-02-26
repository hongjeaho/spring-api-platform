package com.platform.datasource.base.config.database;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        MybatisAutoConfiguration.class})
@Configuration
public class PlatFormDatabaseSource {
    public final static String PLATFORM_DATASOURCE = "platFormDataSource";
    public final static String PLATFORM_DATASOURCE_MANAGER = "platFormTransactionManager";
    public static final String PLATFORM_DOMAIN_JDBC_TEMPLATE = "platFormDomainJdbcTemplate";
    public static final String PLATFORM_DOMAIN_NAMED_PARAMETER_JDBC_OPERATIONS = "platFormDomainNamedParameterJdbcOperations";

    @Bean(PLATFORM_DATASOURCE)
    @ConfigurationProperties("platform.domain.datasource")
    public DataSource platFormDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(PLATFORM_DATASOURCE_MANAGER)
    public PlatformTransactionManager platFormTransactionManager(@Qualifier(PLATFORM_DATASOURCE) final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = PLATFORM_DOMAIN_NAMED_PARAMETER_JDBC_OPERATIONS)
    public NamedParameterJdbcOperations platFormDomainNamedParameterJdbcOperations() {
        return new NamedParameterJdbcTemplate(platFormDataSource());
    }

    @Bean(name = PLATFORM_DOMAIN_JDBC_TEMPLATE)
    public JdbcTemplate platFormDomainJdbcTemplate(
            @Qualifier(PLATFORM_DATASOURCE) final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
