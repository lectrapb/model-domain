package com.app.infra.adapter.share.common.infra.config;

import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import java.time.Duration;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
public class DatabaseConfig {




    @Bean
    public ConnectionFactory connectionFactoryWrite2() {

        var options = ConnectionFactoryOptions.builder()
                .option(DRIVER, "postgresql")
                .option(HOST, "localhost")
                .option(PORT, 5432)
                .option(USER, "postgres")
                .option(PASSWORD, "postgres")
                .option(DATABASE, "postgres")
                .option(CONNECT_TIMEOUT, Duration.ofSeconds(10))
                .option(MAX_SIZE, 40)
                .build();

        return ConnectionFactoryBuilder
                .withOptions(options.mutate())
                .build();
    }


    @Bean
    @Primary
    public ConnectionFactory connectionFactoryWrite() {
        return ConnectionFactoryBuilder
                .withUrl("r2dbc:postgresql://localhost:5432/postgres")
                .username("postgres")
                .password("postgres")
                .build();
    }




    @Bean
    ConnectionFactoryInitializer initializer(@Qualifier("connectionFactoryWrite")ConnectionFactory connectionFactory){

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        var populate = new CompositeDatabasePopulator();
        populate.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        populate.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
        initializer.setDatabasePopulator(populate);

        return initializer;
    }
}
