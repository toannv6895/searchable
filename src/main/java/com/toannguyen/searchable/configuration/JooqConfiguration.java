package com.toannguyen.searchable.configuration;

import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Configuration
public class JooqConfiguration {
    private final static SQLDialect SQL_DIALECT = SQLDialect.POSTGRES;

    @Bean
    public DefaultDSLContext dslContext(DataSource dataSource) {
        return new DefaultDSLContext(createConfiguration(createConnectionProvider(dataSource)));
    }

    public DataSourceConnectionProvider createConnectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
    }

    public DefaultConfiguration createConfiguration(DataSourceConnectionProvider connProvider) {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.set(SQL_DIALECT);
        configuration.set(connProvider);
        return configuration;
    }
}
