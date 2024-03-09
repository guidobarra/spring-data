package com.guba.spring.elasticsearch.criterial.models;

import lombok.Data;

@Data
public class Filter {
    private String field;
    private Operator operator;
    private String value;
}
