package com.alura.ForumHub.infrastructure.validations.topic;

import com.alura.ForumHub.domain.entities.Topic;

/**
 * UserValidation
 */
public interface TopicValidation {

  void validate(Topic topic);

}
