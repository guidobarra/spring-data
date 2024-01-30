package com.guba.spring.redis.keysvalues.databases.repositories;

import com.guba.spring.redis.keysvalues.web.dto.KeyValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Repository
public class RedisRepository {

    private final StringRedisTemplate template;

    public List<KeyValue> getKey(String pattern, long scanCount) {
        log.info("Scanning the keys with count {} for regex '{}'", scanCount, pattern);
        ScanOptions scanOptions = ScanOptions
                .scanOptions()
                .count(scanCount)
                .match(pattern)
                .build();
        List<String> keys = new ArrayList<>();

        try (var cursor = template.scan(scanOptions) ) {
            cursor.forEachRemaining(keys::add);
        } catch (Exception e) {
            log.error("error in get keys");
        }

        List<Long> values = template
                .opsForValue()
                .multiGet(keys)
                .stream()
                .map(Long::parseLong)
                .toList();

        Iterator<Long> valuesIt = values.iterator();
        //join values and keys (k1, k2, k3...kn) -> (v1, v2, v3....vn)
        return keys
                .stream()
                .map(key -> new KeyValue(key, valuesIt.next()))
                .toList();
    }

    public KeyValue increment(String key, Duration ttl) {
        Long increment = template.opsForValue().increment(key);
        template.expire(key, ttl);

        return new KeyValue(key, increment);

    }

    public KeyValue increment(String key, Long ttl, TimeUnit unit) {
        Long increment = template.opsForValue().increment(key);
        template.expire(key, ttl, unit);

        return new KeyValue(key, increment);
    }
}
