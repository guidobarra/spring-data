package com.guba.spring.elasticsearch.criterial.models;

public enum Operator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GT(">"),
    GTE(">="),
    LT("<"),
    LTE("<="),
    IN("IN"),
    NIN("IN"),
    CONTAINS("CONTAINS"),
    NOT_CONTAINS("NOT_CONTAINS");
    private final String operator;

    Operator(String operator) {
        this.operator = operator;
    }
}
