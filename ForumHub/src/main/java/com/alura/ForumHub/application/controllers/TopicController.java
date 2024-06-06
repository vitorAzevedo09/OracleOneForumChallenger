package com.alura.ForumHub.application.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping
  public ResponseEntity<Page<TopicWithId>> list(Pageable pageable) {
    Page<Topic> topics = topicService.list(pageable);
    return ResponseEntity.ok(topics.map(TopicWithId::new));
  }

  @PostMapping
  public ResponseEntity<TopicWithId> create(
      @Valid @RequestBody TopicWithoutId topic) {
    Topic newTopic = topicService.save(topic.toEntity());
    TopicWithId topicWithId = new TopicWithId(newTopic);
    return ResponseEntity.status(201).body(topicWithId);
  }

}
