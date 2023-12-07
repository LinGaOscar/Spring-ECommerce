package com.oscar.database.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.oscar.database.domain"})
@EnableJpaRepositories(basePackages = {"com.oscar.database.domain"})
public class DaoConfig {
}
