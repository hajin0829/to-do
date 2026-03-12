package com.example.todo.service;

import com.example.todo.dto.TodoCreateRequestDTO;
import com.example.todo.dto.TodoResponseDTO;
import com.example.todo.dto.TodoUpdateRequestDTO;
import com.example.todo.dto.TodoUpdateStatusDTO;
import com.example.todo.entity.Todo;
import com.example.todo.repository.TodoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor // final 필드로 생성자 만듦
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    private TodoResponseDTO todoResponseDTO(Todo todo) {
        return TodoResponseDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .content(todo.getContent())
                .completed(todo.isCompleted())
                .startDate(todo.getStartDate())
                .deadline(todo.getDeadline())
                .createdAt(todo.getCreatedAt())
                .build();
    }


    public Todo create(TodoCreateRequestDTO requestDTO) {
        Todo todo = Todo.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .deadline(requestDTO.getDeadline())
                .build();
        return todoRepository.save(todo);
    }


    public List<TodoResponseDTO> findAll() {
        return todoRepository.findAll().stream()
                .map(TodoResponseDTO::new)
                .toList();
    }

    public TodoResponseDTO findById(Long id) {
        Optional<Todo> result = todoRepository.findById(id);
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당 id의 todo가 없습니다.");
        }
        return new TodoResponseDTO(result.get());
    }

    public TodoResponseDTO findByTitle(String title) {
        Optional<TodoResponseDTO> result = todoRepository.findByTitle(title);
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당 제목의 todo가 없습니다.");
        }
        return result.get();
    }

    public TodoResponseDTO findByContent(String content) {
        Optional<TodoResponseDTO> result = todoRepository.findByContent(content);
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당 내용의 todo가 없습니다.");
        }
        return result.get();
    }

    public TodoResponseDTO findByCompletedTrue() {
        Optional<TodoResponseDTO> result = todoRepository.findByCompletedTrue();
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당되는 todo가 없습니다.");
        }
        return result.get();
    }

    public TodoResponseDTO findByCompletedFalse() {
        Optional<TodoResponseDTO> result = todoRepository.findByCompletedFalse();
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당되는 todo가 없습니다.");
        }
        return result.get();
    }

    public TodoResponseDTO findByDeadlineBefore(LocalDate deadline) {
        Optional<TodoResponseDTO> result = todoRepository.findByDeadlineBefore(deadline);
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당 기간의 todo가 없습니다.");
        }
        return result.get();
    }

    public TodoResponseDTO findByDeadlineAfter(LocalDate deadline) {
        Optional<TodoResponseDTO> result = todoRepository.findByDeadlineAfter(deadline);
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당 기간의 todo가 없습니다.");
        }
        return result.get();
    }

    public TodoResponseDTO findByDeadlineBetween(LocalDate startDate, LocalDate deadline) {
        Optional<TodoResponseDTO> result = todoRepository.findByDeadlineBetween(startDate, deadline);
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당 기간의 todo가 없습니다.");
        }
        return result.get();
    }
    @Transactional
    public void update(Long id, TodoUpdateRequestDTO requestDTO) {
        Optional<Todo> result = todoRepository.findById(id);
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당 id의 todo가 없습니다.");
        }
        Todo todo = result.get();
        if (requestDTO.getTitle() != null) {
            todo.updateTitle(requestDTO.getTitle());
        }
        if (requestDTO.getContent() != null) {
            todo.updateContent(requestDTO.getContent());
        }
    }

    @Transactional
    public void updateCompleted(Long id, TodoUpdateStatusDTO requestDTO) {
        Optional<Todo> result = todoRepository.findById(id);
        Todo todo = result.get();
        todo.updateCompleted(requestDTO.isCompleted());
    }

    @Transactional
    public void delete(Long id) {
        Optional<Todo> result = todoRepository.findById(id);
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당 id의 게시글이 없습니다.");
        }
        Todo todo = result.get();
        todoRepository.delete(todo);

    }


}