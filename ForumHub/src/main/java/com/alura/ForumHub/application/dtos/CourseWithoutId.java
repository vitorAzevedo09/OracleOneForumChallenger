package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.Course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * CourseWithoutId
 */
public record CourseWithoutId(
    @NotNull @NotBlank String name,
    @NotNull @NotBlank String category) {

  public Course toEntity() {
    return Course.builder()
        .name(this.name)
        .category(this.category)
        .build();
  }
}
