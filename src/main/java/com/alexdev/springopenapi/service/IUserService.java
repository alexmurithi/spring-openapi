package com.alexdev.springopenapi.service;

import com.alexdev.springopenapi.dto.UserRequest;
import com.alexdev.springopenapi.dto.UserResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    UserResponse createUser(UserRequest user);

    List<UserResponse> users();

    void deleteUser(UUID userId);

    Optional<UserResponse> getUser(UUID userId);

    UserResponse updateUser(UUID userId,UserRequest userRequest);
}
