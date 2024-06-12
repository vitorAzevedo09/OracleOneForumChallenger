package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.Reply;
import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * ReplyWithoutId
 */
public record ReplyWithoutId(
        @NotNull @NotBlank String message,
        @NotNull @NotBlank Boolean solution,
        @NotNull @NotBlank Long userId,
        @NotNull @NotBlank Long topicId) {

    public Reply toEntity() {
        return Reply
                .builder()
                .message(this.message)
                .solution(this.solution)
                .user(User
                        .builder()
                        .id(userId)
                        .build())
                .topic(Topic
                        .builder()
                        .id(topicId)
                        .build())
                .build();
    }
}
