package com.alura.ForumHub.application.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.ForumHub.application.dtos.ReplyWithId;
import com.alura.ForumHub.application.dtos.ReplyWithoutId;
import com.alura.ForumHub.domain.entities.Reply;
import com.alura.ForumHub.domain.services.ReplyService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

/**
 * ReplyController
 */
@RestController
@RequestMapping("/replies")
@SecurityRequirement(name = "bearerAuth")
public class ReplyController {

  private final ReplyService replyService;

  public ReplyController(ReplyService replyService) {
    this.replyService = replyService;
  }

  @PostMapping
  public ResponseEntity<ReplyWithId> create(
      @Valid @RequestBody ReplyWithoutId reply) {
    Reply newReply = replyService.save(reply.toEntity());
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ReplyWithId(newReply));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    replyService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ReplyWithId> update(
      @PathVariable final Long id,
      @Valid @RequestBody final ReplyWithoutId reply) {
    Reply newReply = replyService.update(id, reply.toEntity());
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ReplyWithId(newReply));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReplyWithId> get(@PathVariable Long id) {
    Reply reply = replyService.findOrFail(id);
    return ResponseEntity
        .ok()
        .body(new ReplyWithId(reply));
  }

  @GetMapping
  public ResponseEntity<Page<ReplyWithId>> list(Pageable pageable) {
    Page<Reply> replies = replyService.findAll(pageable);
    return ResponseEntity
        .ok()
        .body(replies
            .map(ReplyWithId::new));
  }

}
