package com.alura.ForumHub.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.ForumHub.application.dtos.CourseWithId;
import com.alura.ForumHub.application.dtos.CourseWithoutId;
import com.alura.ForumHub.domain.services.CourseService;

import jakarta.validation.Valid;

/**
 * CourseController
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

  private CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @PostMapping
  public ResponseEntity<CourseWithId> create(@Valid @RequestBody CourseWithoutId course) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new CourseWithId(
            courseService.save(course.toEntity())));
  }

}
