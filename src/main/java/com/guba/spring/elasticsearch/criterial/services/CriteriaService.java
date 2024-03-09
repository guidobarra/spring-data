package com.guba.spring.elasticsearch.criterial.services;

import com.guba.spring.elasticsearch.criterial.models.CriteriaDTO;
import com.guba.spring.elasticsearch.criterial.models.Filter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@Service
public class CriteriaService {

    public CriteriaQuery convert(CriteriaDTO criteriaDTO) {
        Optional<Criteria> criteriaQueryAnd = Stream
                .of(
                        generateCriteria(criteriaDTO.getFilters(), Criteria::and),
                        generateCriteria(criteriaDTO.getAnd(), Criteria::and)
                ).filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(Criteria::and);

        CriteriaQuery criteriaQueryTotal = Stream
                .of(
                        criteriaQueryAnd,
                        generateCriteria(criteriaDTO.getOr(), Criteria::or)
                ).filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(Criteria::subCriteria)
                .map(CriteriaQuery::new)
                .orElseThrow(() -> new RuntimeException("error in generate CriteriaQuery total"));

        if (criteriaDTO.getOrder() != null) {
            Sort sort = Sort.by(criteriaDTO.getOrder().getType(), criteriaDTO.getOrder().getField());
            criteriaQueryTotal.addSort(sort);
        }

        if (criteriaDTO.getPage() != null) {
            criteriaQueryTotal.setPageable(PageRequest.of(criteriaDTO.getPage().getNumber(), criteriaDTO.getPage().getSize()));
        }

        criteriaQueryTotal.setTimeout(Duration.of(2, ChronoUnit.SECONDS));
        return criteriaQueryTotal;
    }

    private Optional<Criteria> generateCriteria(List<Filter> filters, BinaryOperator<Criteria> binaryOperator) {
        if (CollectionUtils.isEmpty(filters))
            return Optional.empty();
        return filters
                .stream()
                .map(this::generateCriteria)
                .reduce(binaryOperator);
    }

    private Criteria generateCriteria(Filter filter) {
        Criteria cPartial = new Criteria(filter.getField());
        //ExpressionParser TODO
        List<String> values = Optional
                .ofNullable(filter.getValue())
                .stream()
                .filter(v -> v.startsWith("(") && v.endsWith(")"))
                .map(v -> v.replace("(", "").replace(")", ""))
                .flatMap(v -> Arrays.stream(v.split(",*\\s")))
                .toList();
        return switch (filter.getOperator()) {
            case GT -> cPartial.greaterThan(filter.getValue());
            case GTE -> cPartial.greaterThanEqual(filter.getValue());
            case EQUAL -> cPartial.is(filter.getValue());
            case NOT_EQUAL -> cPartial.is(filter.getValue()).not();
            case LT -> cPartial.lessThan(filter.getValue());
            case LTE -> cPartial.lessThanEqual(filter.getValue());
            case CONTAINS -> cPartial.contains(filter.getValue());
            case NOT_CONTAINS -> cPartial.contains(filter.getValue()).not();
            case IN -> cPartial.in(values);
            case NIN -> cPartial.notIn(values);

        };
    }

}
