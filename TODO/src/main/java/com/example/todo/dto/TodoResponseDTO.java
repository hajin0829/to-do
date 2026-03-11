package com.example.todo.dto;

import com.example.todo.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoResponseDTO {
    private Long id;
    private String title;
    private String content;
    private boolean completed;
    private LocalDate startDate;
    private LocalDate deadline;
    private LocalDateTime createdAt;

    public Todo toEntity() {
        return Todo.builder()
                .id(id)
                .title(title)
                .content(content)
                .completed(completed)
                .startDate(startDate)
                .deadline(deadline)
                .createdAt(createdAt)
                .build();
    }
}