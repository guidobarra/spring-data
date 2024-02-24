package com.guba.spring.elasticsearch.web.mapper;

import com.guba.spring.elasticsearch.databases.elasticsearch.domains.Article;
import com.guba.spring.elasticsearch.databases.elasticsearch.domains.Author;
import com.guba.spring.elasticsearch.web.dto.ArticleDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperArticle {

    public static ArticleDTO toArticleDTO(Article a) {
        Set<String> authorsName = a
                .getAuthors()
                .stream()
                .map(Author::getName)
                .collect(Collectors.toSet());
        return new ArticleDTO(a.getId(), a.getTitle(), authorsName, a.getTags());
    }

    public static Article toArticle(ArticleDTO a) {
        List<Author> authors = a
                .author()
                .stream()
                .map(Author::new)
                .toList();
        return new Article(a.id(), a.title(), authors, a.tags());
    }
}
