package com.alura.ForumHub.application.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alura.ForumHub.application.dtos.TopicWithId;
import com.alura.ForumHub.application.dtos.TopicWithoutId;
import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.services.TopicService;
import static com.alura.ForumHub.infrastructure.repositories.specifications.TopicSpecs.*;

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
  public ResponseEntity<Page<TopicWithId>> list(
      @PageableDefault(sort = { "createdAt", "title" }, size = 10, direction = Direction.ASC) Pageable pageable,
      @RequestParam(name = "courseName", required = false, defaultValue = "") String name,
      @RequestParam(name = "creationYear", required = false, defaultValue = "0") Integer year) {

    Page<Topic> topics = topicService.list(
        courseNameLike(name)
            .and(creationYearIs(year)),
        pageable);

    return ResponseEntity.ok(topics.map(TopicWithId::new));
  }

  @PostMapping
  public ResponseEntity<TopicWithId> create(
      @Valid @RequestBody TopicWithoutId topic) throws Exception {
    Topic newTopic = topicService.save(topic.toEntity());
    TopicWithId topicWithId = new TopicWithId(newTopic);
    return ResponseEntity.status(201).body(topicWithId);
  }

}
