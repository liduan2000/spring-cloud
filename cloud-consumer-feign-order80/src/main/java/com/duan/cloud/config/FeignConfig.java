package com.duan.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer() {
        // Default
//        return Retryer.NEVER_RETRY;

        // The maximum number of requests is 3(1+2)
        // Initial interval time is 100ms
        // The maximum interval between retries is 1s
        return new Retryer.Default(100, 1, 3);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
