package com.alura.ForumHub.infrastructure.validations.course;

import com.alura.ForumHub.domain.entities.Course;
import com.alura.ForumHub.domain.services.CourseService;

/**
 * CourseValidationAlreadyExistName
 */
public class CourseValidationAlreadyExistName implements CourseValidation {

  private final CourseService courseService;

  public CourseValidationAlreadyExistName(CourseService courseService) {
    this.courseService = courseService;
  }

  @Override
  public void validate(Course course) {
    if (courseService.existsByName(course.getName())) {
      throw new IllegalArgumentException("Course already exists");
    }
  }

}
