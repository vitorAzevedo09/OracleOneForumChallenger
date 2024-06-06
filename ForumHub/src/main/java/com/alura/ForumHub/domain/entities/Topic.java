package com.alura.ForumHub.domain.entities;

import java.time.ZonedDateTime;

import com.alura.ForumHub.domain.entities.enums.TopicStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Topic
 */
@Getter
@Setter
@Builder
@Entity
@Table(name = "TOPICS")
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "message", nullable = false)
  private String message;

  @Column(name = "creation_date", nullable = false)
  private final ZonedDateTime creationDate = ZonedDateTime.now();

  @Column(name = "status", nullable = false)
  private TopicStatus status;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

}
