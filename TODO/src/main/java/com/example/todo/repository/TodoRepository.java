package com.example.todo.repository;

import com.example.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
    Optional<Todo> findByTitle(String title);
    Optional<Todo> findByContent(String content);
    Optional<Todo> findByCompletedTrue();
    Optional<Todo> findByCompletedFalse();
    Optional<Todo> findByDeadlineBefore(LocalDate deadline);
    Optional<Todo> findByDeadlineAfter(LocalDate deadline);
    Optional<Todo> findByDeadlineBetween(LocalDate startDate, LocalDate deadline);
}