package com.guba.spring.elasticsearch.web.controllers;

import com.guba.spring.elasticsearch.databases.elasticsearch.domains.Article;
import com.guba.spring.elasticsearch.databases.elasticsearch.repositories.ArticleRepository;
import com.guba.spring.elasticsearch.web.dto.ArticleDTO;
import com.guba.spring.elasticsearch.web.mapper.MapperArticle;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ArticleRepository articleRepository;

    @PostConstruct()
    void getClusterHealth() {
        log.info("elastic cluster health {}", elasticsearchOperations.cluster().health());
    }

    @PostMapping(value = "")
    public ResponseEntity<ArticleDTO> save(@Valid @RequestBody ArticleDTO a) {
        Article article = MapperArticle.toArticle(a);
        var articleSave = articleRepository.save(article);

        return ResponseEntity
                .ok(MapperArticle.toArticleDTO(articleSave));
    }

    @GetMapping(value = "/query/{pattern}")
    public ResponseEntity<List<ArticleDTO>> query(@PathVariable(name = "pattern") String title) {
        log.info("title {}", title);
        Criteria criteria = new Criteria("title").regexp(title)
                //.or("title").is("guido")
                ;
        CriteriaQuery query = new CriteriaQuery(criteria);
        Sort.TypedSort<Article> article = Sort.sort(Article.class);
        article.by(Article::getTitle).ascending();
        query.addSort(article);
        query.setTimeout(Duration.of(2, ChronoUnit.SECONDS));
        query.setPageable(PageRequest.of(0,10));
        //this.articleRepository.findAll(article);
        List<ArticleDTO> articles= this.elasticsearchOperations
                .search(query, Article.class, IndexCoordinates.of("blog"))
                .stream()
                .map(SearchHit::getContent)
                .map(MapperArticle::toArticleDTO)
                .toList();

        return ResponseEntity.ok(articles);
    }
}
