package com.alura.ForumHub.domain.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alura.ForumHub.domain.entities.Course;
import com.alura.ForumHub.domain.repositories.CourseRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

/**
 * CourseService
 */
@Service
public class CourseService {

  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Course findOrFail(final Long id) {
    return courseRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Course with id %d not found", id)));
  }

  public Page<Course> findAll(Pageable pageable) {
    return courseRepository.findAll(pageable);
  }

  @Transactional
  public Course save(Course course) {
    return courseRepository.save(course);
  }

  @Transactional
  public Course update(final Long id, final Course course) {
    course.setId(findOrFail(id)
        .getId());
    return save(course);
  }

  @Transactional
  public void delete(final Long id) {
    Course course = findOrFail(id);
    courseRepository.delete(course);
  }

}
