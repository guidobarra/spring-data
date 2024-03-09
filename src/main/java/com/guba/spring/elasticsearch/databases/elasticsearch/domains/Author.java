package com.guba.spring.elasticsearch.databases.elasticsearch.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Author {
    @Field(type = FieldType.Keyword, name = "name")
    private String name;
}
