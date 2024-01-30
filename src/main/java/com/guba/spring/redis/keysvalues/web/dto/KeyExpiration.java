package com.guba.spring.redis.keysvalues.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.concurrent.TimeUnit;

public record KeyExpiration(
        @NotEmpty(message = "Key is mandatory")
        String key,
        @NotNull(message = "TTL is mandatory")
        Long ttl,
        @NotNull(message = "TimeUnit is mandatory")
        TimeUnit timeUnit
) { }