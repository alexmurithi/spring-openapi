package com.alexdev.springopenapi.controller;

import com.alexdev.springopenapi.api.UsersApi;
import com.alexdev.springopenapi.dto.UserRequest;
import com.alexdev.springopenapi.dto.UserResponse;
import com.alexdev.springopenapi.service.UserService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@ConditionalOnWebApplication
public class UserController implements UsersApi {
    private final UserService userService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable UUID userId) {
        try{
            userService.deleteUser(userId);
        }catch (EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
        }

    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable UUID userId) {
        return userService
                .getUser(userId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable UUID userId, @Valid @RequestBody UserRequest userRequest) {
        try {
            return userService.updateUser(userId, userRequest);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> users() {
        return userService.users();
    }
}
