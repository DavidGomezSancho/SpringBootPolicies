package com.home.policies.config;

import java.util.concurrent.TimeUnit;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfiguration {
	@Bean
	public CaffeineCache cache() {
	    return new CaffeineCache("policy", Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .maximumSize(100)
                .build());
	}
	
//	@Bean
//	public CaffeineCache cacheB() {
//	    return new CaffeineCache("otherPolicy", Caffeine.newBuilder()
//                .expireAfterWrite(20, TimeUnit.SECONDS)
//                .maximumSize(10)
//                .build());
//	}
}
