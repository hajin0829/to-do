package com.example.todo.dto;

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
    private Long ID;
    private String title;
    private String content;
    private LocalDate deadline;
    private LocalDateTime createdAt;
}