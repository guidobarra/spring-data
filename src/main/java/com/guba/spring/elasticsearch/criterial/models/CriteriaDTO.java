package com.guba.spring.elasticsearch.criterial.models;


import lombok.Data;

import java.util.List;

@Data
public class CriteriaDTO {
    private List<Filter> filters;
    private List<Filter> or;
    private List<Filter> and;
    private Order order;
    private Page page;
}
