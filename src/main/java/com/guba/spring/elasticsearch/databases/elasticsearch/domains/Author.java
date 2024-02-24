package com.guba.spring.elasticsearch.databases.elasticsearch.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@AllArgsConstructor
@Getter
public class Author {
    @Field(type = FieldType.Text)
    private String name;
}
