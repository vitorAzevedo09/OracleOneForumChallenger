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

  private final TopicRepository tRepository;

  public TopicValidationDuplicate(TopicRepository topicRepository) {
    this.tRepository = topicRepository;
  }

  @Override
  public void validate(Topic topic) {
    if (isDuplicated(topic)) {
      throw new ValidationException("Topic must not be duplicated");
    }
  }

  public boolean isDuplicated(Topic topic) {
    return tRepository
        .existsByTitleAndMessage(
            topic.getTitle(),
            topic.getMessage());
  }

}
