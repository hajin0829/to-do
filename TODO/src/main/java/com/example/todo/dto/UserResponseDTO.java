package com.example.todo.dto;

import com.example.todo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long userId;
    private String email;
    private String password;
    private String name;
    private LocalDate userCreatedAt;

    public UserResponseDTO(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.userCreatedAt = user.getUserCreatedAt();
    }
}