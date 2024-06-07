package com.alura.ForumHub.domain.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.repositories.TopicRepository;
import com.alura.ForumHub.infrastructure.validations.topic.TopicValidation;

import jakarta.transaction.Transactional;

/**
 * TopicService
 */
@Service
public class TopicService {

  private final TopicRepository topicRepository;
  private final UserService userService;
  private final CourseService courseService;
  private final List<TopicValidation> topicValidations;

  public TopicService(
      TopicRepository topicRepository,
      UserService userService,
      CourseService courseService,
      List<TopicValidation> topicValidations) {
    this.topicRepository = topicRepository;
    this.topicValidations = topicValidations;
    this.userService = userService;
    this.courseService = courseService;
  }

  @Transactional
  public Topic save(Topic topic) {
    topicValidations.forEach(validation -> validation.validate(topic));
    topic.setAuthor(userService.findOrFail(topic.getAuthor()));
    topic.setCourse(courseService.findOrFail(topic.getCourse()));
    return topicRepository.save(topic);
  }

  public Page<Topic> list(Pageable pageable) {
    return topicRepository.findAll(pageable);
  }

}
