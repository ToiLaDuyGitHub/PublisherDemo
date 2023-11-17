package com.example.jwttutorial.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

public class CacheConfig {
    public static final String USER = "USER";

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder().expireAfterWrite(1440, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager(USER);
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}