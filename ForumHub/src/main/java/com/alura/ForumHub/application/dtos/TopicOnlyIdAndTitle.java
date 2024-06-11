package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.Topic;

/**
 * TopicOnlyIdAndTitle
 */
public record TopicOnlyIdAndTitle(
    Long id,
    String title) {

  public TopicOnlyIdAndTitle(Topic topic) {
    this(topic.getId(), topic.getTitle());
  }
}
