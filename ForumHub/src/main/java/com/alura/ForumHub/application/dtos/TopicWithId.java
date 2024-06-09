package com.alura.ForumHub.application.dtos;

import java.time.ZonedDateTime;

import com.alura.ForumHub.domain.entities.Topic;
import com.alura.ForumHub.domain.entities.enums.TopicStatus;

/**
 * TopicWithID
 */
public record TopicWithId(
                                Long id,
                                String title,
                                String message,
                                ZonedDateTime createdAt,
                                TopicStatus status,
                                AuthorWithIdAndName author,
                                CourseWithIdAndName course) {

                public TopicWithId(Topic topic) {
                                this(
                                                                topic.getId(),
                                                                topic.getTitle(),
                                                                topic.getMessage(),
                                                                topic.getCreatedAt(),
                                                                topic.getStatus(),
                                                                new AuthorWithIdAndName(topic.getAuthor()),
                                                                new CourseWithIdAndName(topic.getCourse()));
                }

}
