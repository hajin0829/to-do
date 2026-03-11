package com.example.todo.repository;

import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
    Optional<TodoResponseDTO> findByTitle(String title);
    Optional<TodoResponseDTO> findByContent(String content);
    Optional<TodoResponseDTO> findByCompletedTrue();
    Optional<TodoResponseDTO> findByCompletedFalse();
    Optional<TodoResponseDTO> findByDeadlineBefore(LocalDate deadline);
    Optional<TodoResponseDTO> findByDeadlineAfter(LocalDate deadline);
    Optional<TodoResponseDTO> findByDeadlineBetween(LocalDate startDate, LocalDate deadline);
}