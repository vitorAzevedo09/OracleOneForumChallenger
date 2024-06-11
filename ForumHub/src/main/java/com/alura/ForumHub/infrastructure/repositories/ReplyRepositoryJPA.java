package com.alura.ForumHub.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.ForumHub.domain.entities.Reply;

/**
 * ReplyRepositoryJPA
 */
@Repository
public interface ReplyRepositoryJPA extends JpaRepository<Reply, Long> {

}
