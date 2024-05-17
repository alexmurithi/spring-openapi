package com.alexdev.springopenapi.service;

import com.alexdev.springopenapi.dto.UserRequest;
import com.alexdev.springopenapi.dto.UserResponse;
import com.alexdev.springopenapi.entity.User;
import com.alexdev.springopenapi.mapper.UserMapper;
import com.alexdev.springopenapi.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(userMapper.toEntity(user));

        return userMapper.toDto(savedUser);
    }

    @Override
    public List<UserResponse> users() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    public void deleteUser(UUID userId) {
         userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User Not Found"));
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<UserResponse> getUser(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto);
    }

    @Override
    public UserResponse updateUser(UUID userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User Not Found"));
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

}
