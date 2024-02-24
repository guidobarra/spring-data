package com.guba.spring.elasticsearch;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class ElasticSearchApp {

    public static void main(String[] args) {
        log.debug("Startup app java spring elasticsearch");
        SpringApplication.run(ElasticSearchApp.class, args);
    }
}
