package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.Course;

/**
 * CourseWithId
 */
public record CourseWithId(Long id, String name) {

  public CourseWithId(Course course) {
    this(course.getId(), course.getName());
  }
}
