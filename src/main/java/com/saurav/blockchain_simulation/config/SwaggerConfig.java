package com.saurav.blockchain_simulation.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi blockchainApi() {
        return GroupedOpenApi.builder()
                .group("blockchain")
                .packagesToScan("com.saurav.blockchain_simulation")
                .build();
    }
}
