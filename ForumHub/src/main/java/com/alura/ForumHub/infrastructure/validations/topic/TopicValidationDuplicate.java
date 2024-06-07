package com.alura.ForumHub.infrastructure.validations.topic;

import org.springframework.stereotype.Component;

import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.repositories.TopicRepository;
import com.alura.ForumHub.infrastructure.exception.ValidationException;

/**
 * UserValidationDuplicate
 */
@Component
public class TopicValidationDuplicate implements TopicValidation {

  private final TopicRepository topicRepository;

  public TopicValidationDuplicate(TopicRepository topicRepository) {
    this.topicRepository = topicRepository;
  }

  @Override
  public void validate(Topic topic) {
    if (topicRepository.existsByTitleAndMessage(topic.getTitle(), topic.getMessage())) {
      throw new ValidationException("Topic must not be duplicated");
    }
  }

}
