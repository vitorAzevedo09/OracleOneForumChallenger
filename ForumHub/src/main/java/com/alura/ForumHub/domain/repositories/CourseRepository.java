package com.alura.ForumHub.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.ForumHub.domain.entities.Course;

/**
 * CourseRepository
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
