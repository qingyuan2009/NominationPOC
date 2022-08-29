package com.sap.s4hana.eureka.business.nomination.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "file:./mock/local/postgres.properties", ignoreResourceNotFound = true)
@PropertySource(value = "file:/etc/secrets/postgres/postgres.properties", ignoreResourceNotFound = true)
public class DataSourceConfig {
}

