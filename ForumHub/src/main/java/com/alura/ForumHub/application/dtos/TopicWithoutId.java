package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.Course;
import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * TopicCreateDTO
 */
public record TopicWithoutId(
                @NotNull @NotBlank String title,
                @NotNull @NotBlank String message,
                @NotNull Long author,
                @NotNull Long course) {

        public Topic toEntity() {
                return Topic.builder()
                                .title(this.title)
                                .message(this.message)
                                .author(User.builder()
                                                .id(this.author)
                                                .build())
                                .course(Course.builder()
                                                .id(this.course)
                                                .build())
                                .build();
        }

}
