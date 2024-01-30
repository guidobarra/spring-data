package com.guba.spring.redis.keysvalues.web.controllers;

import com.guba.spring.redis.keysvalues.databases.repositories.RedisRepository;
import com.guba.spring.redis.keysvalues.web.dto.KeyExpiration;
import com.guba.spring.redis.keysvalues.web.dto.KeyValue;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/key-value")
public class KeyValueController {

    private final RedisRepository redisRepository;

    @PostMapping(value = "/increment")
    public ResponseEntity<KeyValue> setKey(@Valid @RequestBody KeyExpiration key) {
        var body = redisRepository.increment(key.key(), key.ttl(), key.timeUnit());
        return ResponseEntity
                .ok(body);
    }

    @GetMapping(value = "/query/{pattern}")
    public ResponseEntity<List<KeyValue>> query(@PathVariable(name = "pattern") String pattern) {
        var body = redisRepository.getKey(pattern, 100);
        return ResponseEntity.ok(body);
    }
}
