package com.alura.ForumHub.application.dtos;

import com.alura.ForumHub.domain.entities.Topic;

/**
 * TopicWithID
 */
public record TopicWithId(
        Long id,
        String title,
        String message,
        AuthorWithIdAndName author,
        CourseWithIdAndName course) {

    public TopicWithId(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                new AuthorWithIdAndName(topic.getAuthor()),
                new CourseWithIdAndName(topic.getCourse()));
    }

}
