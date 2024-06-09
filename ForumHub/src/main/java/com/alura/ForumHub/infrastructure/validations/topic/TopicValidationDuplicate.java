package com.alura.ForumHub.infrastructure.validations.topic;

import org.springframework.stereotype.Component;

import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.services.TopicService;
import com.alura.ForumHub.infrastructure.exception.ValidationException;

/**
 * UserValidationDuplicate
 */
@Component
public class TopicValidationDuplicate implements TopicValidation {

  private final TopicService service;

  public TopicValidationDuplicate(TopicService service) {
    this.service = service;
  }

  @Override
  public void validate(Topic topic) {
    if (service.isDuplicated(topic)) {
      throw new ValidationException("Topic must not be duplicated");
    }
  }

}
