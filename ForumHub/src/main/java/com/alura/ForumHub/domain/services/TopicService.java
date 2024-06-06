package com.alura.ForumHub.domain.services;

import org.springframework.stereotype.Service;

import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.repositories.TopicRepository;

/**
 * TopicService
 */
@Service
public class TopicService {

  private final TopicRepository topicRepository;

  public TopicService(TopicRepository topicRepository) {
    this.topicRepository = topicRepository;
  }

  private Boolean alreadyExists(Topic topic) {
    return topicRepository.existsByTitleAndMessage(topic.getTitle(), topic.getMessage());
  }

  public Topic save(Topic topic) {
    if (alreadyExists(topic)) {
      throw new IllegalArgumentException("Topic must not be duplicated");
    }
    return topicRepository.save(topic);
  }

}
