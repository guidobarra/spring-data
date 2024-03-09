package com.guba.spring.elasticsearch.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public record ArticleDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        String id,
        @NotEmpty(message = "title is mandatory")
        String title,
        @NotNull(message = "authors is mandatory")
        Set<String> author,
        String[] tags,
        @NotEmpty(message = "type is mandatory")
        String type,
        @NotEmpty(message = "category is mandatory")
        String category,
        @NotNull(message = "price is mandatory")
        Double price
) {
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ArticleDTO that = (ArticleDTO) o;
                return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Arrays.equals(tags, that.tags) && Objects.equals(type, that.type) && Objects.equals(category, that.category) && Objects.equals(price, that.price);
        }

        @Override
        public int hashCode() {
                int result = Objects.hash(id, title, author, type, category, price);
                result = 31 * result + Arrays.hashCode(tags);
                return result;
        }

        @Override
        public String toString() {
                return "ArticleDTO{" +
                        "id='" + id + '\'' +
                        ", title='" + title + '\'' +
                        ", author=" + author +
                        ", tags=" + Arrays.toString(tags) +
                        ", type='" + type + '\'' +
                        ", category='" + category + '\'' +
                        ", price=" + price +
                        '}';
        }
}