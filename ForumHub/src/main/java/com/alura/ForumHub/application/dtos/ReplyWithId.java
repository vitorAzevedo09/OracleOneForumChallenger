package com.alura.ForumHub.application.dtos;

import java.time.ZonedDateTime;

import com.alura.ForumHub.domain.entities.Reply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * ReplyWithId
 */
public record ReplyWithId(
        @NotNull @NotBlank String message,
        @NotNull @NotBlank ZonedDateTime creationDate,
        @NotNull @NotBlank Boolean solution,
        @NotNull @NotBlank UserOnlyIdAndName user,
        @NotNull @NotBlank TopicOnlyIdAndTitle topic,
        @NotNull @NotBlank Long id) {

    public ReplyWithId(Reply reply) {
        this(
                reply.getMessage(),
                reply.getCreationDate(),
                reply.getSolution(),
                new UserOnlyIdAndName(reply.getUser()),
                new TopicOnlyIdAndTitle(reply.getTopic()),
                reply.getId());
    }
}
