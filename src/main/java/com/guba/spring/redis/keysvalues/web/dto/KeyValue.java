package com.guba.spring.redis.keysvalues.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record KeyValue (
        @NotEmpty(message = "Key is mandatory")
        String key,
        @NotNull(message = "Value is mandatory")
        Long value
) { }
