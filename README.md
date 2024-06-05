# Oracle One Forum Hub Challenger - Spring Boot

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Diagrams](#diagrams)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction
  The Oracle One Forum Hub Challenger is a Spring Boot application designed
  to serve as a forum hub. This project is a challenge by Oracle aimed at fostering
  learning and development among developers. The application allows users to create, 
  view, and participate in discussion threads.

## Features

 * User registration and authentication
 * Create, read, update, and delete (CRUD) operations for forum posts
 * Commenting system for posts
 * Categorization of posts by topics
 * Search functionality

## Diagrams
  Some Diagrams to clarify features.

### Entity Relationship Diagram 

  ```mermaid
  erDiagram
    USER {
        int id
        string name
        string email
        string password
    }
    
    PROFILE {
        int id
        string name
    }
    
    USER ||--|{ PROFILE: "profiles"
    
    COURSE {
        int id
        string name
        string category
    }
    
    TOPIC {
        int id
        string title
        string message
        datetime creationDate
        string status
    }
    
    TOPIC }o--|| USER: "author"
    TOPIC }o--|| COURSE: "course"
    
    REPLY {
        int id
        string message
        datetime creationDate
        boolean solution
    }
    
    REPLY }o--|| USER: "author"
    REPLY }o--|| TOPIC: "topic"
    
    TOPIC ||--|{ REPLY: "replies"
```

### Class Diagram


```mermaid
    sequenceDiagram
    actor User
    participant AuthService
    participant UserService
    participant PostService
    participant CommentService
    participant SearchService
    participant CourseService

    User ->> AuthService: register(name, email, password)
    AuthService ->> UserService: createUser(name, email, password)
    UserService ->> AuthService: userCreated
    AuthService ->> User: registrationSuccess

    User ->> AuthService: login(email, password)
    AuthService ->> UserService: authenticate(email, password)
    UserService ->> AuthService: authenticationSuccess
    AuthService ->> User: loginSuccess

    User ->> PostService: createPost(title, message, courseId)
    PostService ->> CourseService: validateCourse(courseId)
    CourseService ->> PostService: courseValid
    PostService ->> UserService: getUser(userId)
    UserService ->> PostService: user
    PostService ->> User: postCreated

    User ->> PostService: readPost(postId)
    PostService ->> User: postDetails

    User ->> PostService: updatePost(postId, newTitle, newMessage)
    PostService ->> UserService: validateUser(userId)
    UserService ->> PostService: userValid
    PostService ->> User: postUpdated

    User ->> PostService: deletePost(postId)
    PostService ->> UserService: validateUser(userId)
    UserService ->> PostService: userValid
    PostService ->> User: postDeleted

    User ->> CommentService: createComment(postId, message)
    CommentService ->> PostService: validatePost(postId)
    PostService ->> CommentService: postValid
    CommentService ->> UserService: getUser(userId)
    UserService ->> CommentService: user
    CommentService ->> User: commentCreated

    User ->> CommentService: readComment(commentId)
    CommentService ->> User: commentDetails

    User ->> CommentService: updateComment(commentId, newMessage)
    CommentService ->> UserService: validateUser(userId)
    UserService ->> CommentService: userValid
    CommentService ->> User: commentUpdated

    User ->> CommentService: deleteComment(commentId)
    CommentService ->> UserService: validateUser(userId)
    UserService ->> CommentService: userValid
    CommentService ->> User: commentDeleted

    User ->> SearchService: searchPost(keyword)
    SearchService ->> PostService: searchByKeyword(keyword)
    PostService ->> SearchService: searchResults
    SearchService ->> User: displaySearchResults

    User ->> SearchService: searchByCategory(categoryId)
    SearchService ->> CourseService: getCoursePosts(categoryId)
    CourseService ->> SearchService: coursePosts
    SearchService ->> User: displaySearchResults


```





