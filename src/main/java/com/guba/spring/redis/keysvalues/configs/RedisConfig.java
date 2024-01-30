package com.guba.spring.redis.keysvalues.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

    @Autowired
    public void enableTransactions(StringRedisTemplate redisTemplate) {
        redisTemplate.setEnableTransactionSupport(true);
    }
}
