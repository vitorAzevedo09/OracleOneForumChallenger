package com.alura.ForumHub.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alura.ForumHub.domain.entities.Topic;

/**
 * TopicRepository
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

  @Query("SELECT t FROM Topic t WHERE t.title = :title AND t.message = :message")
  Boolean existsByTitleAndMessage(String title, String message);

}
