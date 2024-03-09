package com.guba.spring.elasticsearch.criterial.models;

import lombok.Data;

import java.util.List;

@Data
public class Filters {
    private List<Filter> filters;
    private List<Filter> or;
    private List<Filter> and;
}
