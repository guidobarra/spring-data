package com.guba.spring.elasticsearch.databases.elasticsearch.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.List;

@Document(indexName = "blog")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Article {

    @Id
    private String id;

    @Field(name = "title", type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Nested, includeInParent = true, name = "authors")
    private List<Author> authors;

    @Field(type = FieldType.Keyword, name = "tags")
    private String[] tags;
    
}