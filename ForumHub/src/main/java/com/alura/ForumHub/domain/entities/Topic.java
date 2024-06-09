package com.alura.ForumHub.domain.entities;

import java.time.ZonedDateTime;

import com.alura.ForumHub.domain.entities.enums.TopicStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Topic
 */
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

  @Column(name = "created_at", nullable = false)
  private final ZonedDateTime createdAt = ZonedDateTime.now();

  @Column(name = "status", nullable = false)
  private final TopicStatus status = TopicStatus.OPEN;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false)
  private User author;

}
