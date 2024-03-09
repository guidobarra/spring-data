package com.guba.spring.elasticsearch.criterial.models;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class Order {
    private String field;
    private Sort.Direction type = Sort.Direction.DESC;
}
