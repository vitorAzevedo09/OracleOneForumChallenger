package com.alura.ForumHub.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.ForumHub.domain.entities.Course;

/**
 * CourseRepository
 */
@Repository
public interface CourseRepositoryJPA extends JpaRepository<Course, Long> {

}
