package com.example.todo.service;

import com.example.todo.dto.UserCreateRequestDTO;
import com.example.todo.dto.UserResponseDTO;
import com.example.todo.dto.UserUpdateRequestDTO;
import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Getter
@RequiredArgsConstructor // final 필드로 생성자 만듦
@Service
public class UserService {
    private final UserRepository userRepository;

    private UserResponseDTO userResponseDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .userCreatedAt(user.getUserCreatedAt())
                .build();
    }

    public User create(UserCreateRequestDTO requestDTO) {
        User user = User.builder()
                .email(requestDTO.getEmail())
                .name(requestDTO.getName())
                .password(requestDTO.getPassword())
                .build();
        return userRepository.save(user);
    }

    public UserResponseDTO findByUserName(String userName) {
        Optional<UserResponseDTO> result = userRepository.findByUserName(userName);
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당 name의 user가 없습니다.");
        }
        return result.get();
    }

    public UserResponseDTO findByUserId(Long userId) {
        Optional<UserResponseDTO> result = userRepository.findByUserId(userId);
        if (result.isEmpty()) {
            throw new InvalidTodoException("해당 id의 user가 없습니다.");
        }
        return result.get();
    }

    @Transactional
    public void updateUser(Long userId, UserUpdateRequestDTO requestDTO) {
        Optional<UserResponseDTO> result = userRepository.findByUserId(userId);
        User user = User.builder()
                .email(requestDTO.getEmail())
                .name(requestDTO.getName())
                .password(requestDTO.getPassword())
                .build();
        if (requestDTO.getName() != null) {
            user.updateName(requestDTO.getName());
        }
        if (requestDTO.getEmail() != null) {
            user.updateEmail(requestDTO.getEmail());
        }
        if (requestDTO.getPassword() != null) {
            user.updatePassword(requestDTO.getPassword());
        }
    }



}