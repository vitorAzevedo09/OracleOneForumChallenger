package com.alura.ForumHub.application.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.ForumHub.application.dtos.CourseWithId;
import com.alura.ForumHub.application.dtos.CourseWithoutId;
import com.alura.ForumHub.domain.services.CourseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

/**
 * CourseController
 */
@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearerAuth")
public class CourseController {

  private CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  public ResponseEntity<Page<CourseWithId>> getAll(Pageable pageable) {
    return ResponseEntity.ok(
        courseService
            .findAll(pageable).map(CourseWithId::new));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseWithId> getOne(final Long id) {
    return ResponseEntity
        .ok()
        .body(new CourseWithId(
            courseService
                .findOrFail(id)));
  }

  @PostMapping
  public ResponseEntity<CourseWithId> create(@Valid @RequestBody CourseWithoutId course) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new CourseWithId(
            courseService.save(course.toEntity())));
  }

  @PostMapping("/{id}")
  public ResponseEntity<CourseWithId> update(
      @PathVariable final Long id,
      @Valid @RequestBody CourseWithoutId course) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new CourseWithId(
            courseService
                .update(id, course.toEntity())));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable final Long id) {
    courseService
        .delete(id);
    return ResponseEntity
        .noContent()
        .build();
  }

}
