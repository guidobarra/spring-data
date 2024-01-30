package com.guba.spring.redis;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class RedisApp {

    public static void main(String[] args) {
        log.debug("Startup app java spring template");
        SpringApplication.run(RedisApp.class, args);
    }
}
