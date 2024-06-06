package com.alura.ForumHub.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.ForumHub.application.dtos.TopicWithId;
import com.alura.ForumHub.application.dtos.TopicWithoutId;
import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.services.TopicService;

import jakarta.validation.Valid;

/**
 * TopicController
 */
@RestController
@RequestMapping("/topics")
public class TopicController {

  private final TopicService topicService;

  public TopicController(TopicService topicService) {
    this.topicService = topicService;
  }

  @PostMapping
  public ResponseEntity<TopicWithId> create(
      @Valid @RequestBody TopicWithoutId topic) {
    Topic newTopic = topicService.save(topic.toEntity());
    TopicWithId topicWithId = new TopicWithId(newTopic);
    return ResponseEntity.status(201).body(topicWithId);
  }

}
