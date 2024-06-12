package com.alura.ForumHub.domain.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.ForumHub.domain.entities.Reply;
import com.alura.ForumHub.domain.repositories.ReplyRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

/**
 * ReplyService
 */
@Service
public class ReplyService {

  private final ReplyRepository replyRepository;
  private TopicService topicService;
  private UserService userService;

  public ReplyService(
      ReplyRepository replyRepository,
      TopicService topicService,
      UserService userService) {
    this.replyRepository = replyRepository;
    this.topicService = topicService;
    this.userService = userService;
  }

  private EntityNotFoundException replyNotFoundException(Long id) {
    return new EntityNotFoundException(
        String.format("Reply with id %d not found", id));
  }

  public Reply findOrFail(Long id) {
    return replyRepository
        .findById(id)
        .orElseThrow(() -> replyNotFoundException(id));
  }

  public Page<Reply> findAll(Pageable pageable) {
    return replyRepository
        .findAll(pageable);
  }

  @Transactional
  public Reply save(Reply reply) {
    reply.setTopic(topicService
        .findOrFail(reply
            .getTopic()
            .getId()));

    reply.setUser(userService
        .findOrFail(reply
            .getUser()
            .getId()));

    return replyRepository.save(reply);
  }

  @Transactional
  public Reply update(final Long id, final Reply reply) {
    reply.setId(findOrFail(id)
        .getId());
    return save(reply);
  }

  @Transactional
  public void delete(final Long id) {
    Reply reply = findOrFail(id);
    replyRepository.delete(reply);
  }

}
