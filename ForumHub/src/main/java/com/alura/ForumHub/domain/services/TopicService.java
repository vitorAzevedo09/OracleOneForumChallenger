package com.alura.ForumHub.domain.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.repositories.TopicRepository;
import com.alura.ForumHub.infrastructure.validations.topic.TopicValidation;

import jakarta.persistence.EntityNotFoundException;
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

  private EntityNotFoundException throwNotFoundException(Long id) {
    return new EntityNotFoundException(
        String.format("Topic with id %d not found", id));
  }

  public Topic findOrFail(Long id) {
    return topicRepository
        .findById(id)
        .orElseThrow(() -> throwNotFoundException(id));
  }

  @Transactional
  public Topic save(Topic topic) {
    topicValidations.forEach(validation -> validation.validate(topic));
    topic.setAuthor(userService.findOrFail(topic
        .getAuthor()
        .getId()));
    topic.setCourse(courseService.findOrFail(topic
        .getCourse()
        .getId()));
    return topicRepository.save(topic);
  }

  public Page<Topic> list(Specification<Topic> specification, Pageable pageable) {
    return topicRepository.findAll(specification, pageable);
  }

  @Transactional
  public Topic update(final Long id, Topic topic) {
    topicValidations.forEach(validation -> validation.validate(topic));

    topic.setId(findOrFail(id).getId());
    topic.setAuthor(userService.findOrFail(topic
        .getAuthor()
        .getId()));
    topic.setCourse(courseService.findOrFail(topic
        .getCourse()
        .getId()));

    return topicRepository.save(topic);
  }

  @Transactional
  public void delete(final Long id) {
    Boolean existInDB = topicRepository.existsById(id);
    if (!existInDB) {
      throw throwNotFoundException(id);
    }
    topicRepository.deleteById(id);
  }

}
