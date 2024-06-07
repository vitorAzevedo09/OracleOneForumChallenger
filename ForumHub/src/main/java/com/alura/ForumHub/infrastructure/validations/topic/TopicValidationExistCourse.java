package com.alura.ForumHub.infrastructure.validations.topic;

import org.springframework.stereotype.Component;

import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.services.CourseService;

/**
 * TopicValidationExistCourse
 */
@Component
public class TopicValidationExistCourse implements TopicValidation {

  private final CourseService courseService;

  public TopicValidationExistCourse(CourseService courseService) {
    this.courseService = courseService;
  }

  @Override
  public void validate(Topic topic) {
    courseService.existCourseOrFail(topic.getCourse());
  }

}
