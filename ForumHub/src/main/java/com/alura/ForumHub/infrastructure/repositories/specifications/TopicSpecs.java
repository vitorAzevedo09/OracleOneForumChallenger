package com.alura.ForumHub.infrastructure.repositories.specifications;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.alura.ForumHub.domain.entities.Topic;

/**
 * TopicSpecifications
 */
public class TopicSpecs {

  public static Specification<Topic> courseNameLike(String name) {
    return (root, query, criteriaBuilder) -> criteriaBuilder
        .like(root.get("course").get("name"), "%" + name + "%");
  }

  public static Specification<Topic> creationYearIs(Integer year) {
    int yearFinal = year == 0 ? 9999 : year + 1;
    return (root, query, criteriaBuilder) -> criteriaBuilder
        .between(
            root.get("createdAt").as(ZonedDateTime.class),
            intToZonedDateTime(year),
            intToZonedDateTime(yearFinal));
  }

  private static ZonedDateTime intToZonedDateTime(int year) {
    return ZonedDateTime.of(year, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
  }
}
