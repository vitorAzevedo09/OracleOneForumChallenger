package com.alura.ForumHub.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alura.ForumHub.domain.entities.Topic;

/**
 * TopicRepository
 */
@Repository
public interface TopicRepositoryJPA extends JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic> {

  @Query("SELECT COUNT(t) > 0 FROM Topic t WHERE t.title = :title AND t.message = :message")
  public Boolean existsByTitleAndMessage(@Param("title") String title, @Param("message") String message);

}
