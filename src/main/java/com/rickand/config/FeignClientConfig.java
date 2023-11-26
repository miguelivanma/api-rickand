package com.rickand.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    @Bean
    Logger.Level feingLoggerLevel(){
        return Logger.Level.FULL;
    }
}
