package com.example.todo.repository;

import com.example.todo.dto.UserResponseDTO;
import com.example.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserResponseDTO> findByUserName(String name);
    Optional<UserResponseDTO> findByUserId(Long userId);
}