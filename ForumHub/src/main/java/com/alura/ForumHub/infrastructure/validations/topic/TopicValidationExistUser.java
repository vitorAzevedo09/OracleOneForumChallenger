package com.alura.ForumHub.infrastructure.validations.topic;

import org.springframework.stereotype.Component;

import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.services.UserService;

/**
 * TopicValidationExistUser
 */
@Component
public class TopicValidationExistUser implements TopicValidation {

  private final UserService userService;

  public TopicValidationExistUser(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void validate(Topic topic) {
    userService.existUserOrFail(topic.getAuthor());
  }
}
