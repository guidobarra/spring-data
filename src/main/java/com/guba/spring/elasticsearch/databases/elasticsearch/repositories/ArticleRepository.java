package com.guba.spring.elasticsearch.databases.elasticsearch.repositories;

import com.guba.spring.elasticsearch.databases.elasticsearch.domains.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    //Page<Article> findByAuthorsName(String name, Pageable pageable);

    //@Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
    //Page<Article> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);

    //@Query("{\"bool\": {\"must\": {\"match_all\": {}}, \"filter\": {\"term\": {\"tags\": \"?0\" }}}}")
    //Page<Article> findByFilteredTagQuery(String tag, Pageable pageable);

    //@Query("{\"bool\": {\"must\": {\"match\": {\"authors.name\": \"?0\"}}, \"filter\": {\"term\": {\"tags\": \"?1\" }}}}")
    //Page<Article> findByAuthorsNameAndFilteredTagQuery(String name, String tag, Pageable pageable);
}
