package com.alexdev.springopenapi.mapper;

import com.alexdev.springopenapi.dto.UserRequest;
import com.alexdev.springopenapi.dto.UserResponse;
import com.alexdev.springopenapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toDto(User user);

    @Mapping(target = "id", ignore = true) // Ignore ID as it should not be set from a request
    User toEntity(UserRequest userRequest);
}
