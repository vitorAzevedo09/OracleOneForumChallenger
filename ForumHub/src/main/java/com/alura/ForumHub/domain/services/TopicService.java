package com.alura.ForumHub.domain.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.repositories.TopicRepository;
import com.alura.ForumHub.infrastructure.validations.topic.TopicValidation;

/**
 * TopicService
 */
@Service
public class TopicService {

  private final TopicRepository topicRepository;
  private final List<TopicValidation> topicValidations;

  public TopicService(TopicRepository topicRepository, List<TopicValidation> topicValidations) {
    this.topicRepository = topicRepository;
    this.topicValidations = topicValidations;
  }

  public Topic save(Topic topic) {
    topicValidations.forEach(validation -> validation.validate(topic));
    return topicRepository.save(topic);
  }

  public Page<Topic> list(Pageable pageable) {
    return topicRepository.findAll(pageable);
  }

}
