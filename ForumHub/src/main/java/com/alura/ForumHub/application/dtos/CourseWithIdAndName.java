package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.Course;

/**
 * CourseWithIdAndName
 */
public record CourseWithIdAndName(
    Long id,
    String name) {

  public CourseWithIdAndName(Course course) {
    this(course.getId(), course.getName());
  }
}
