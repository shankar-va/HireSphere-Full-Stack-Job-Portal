package com.naukri.driver.mapper.user;

import com.naukri.driver.dto.request.user.UserRegistrationRequest;
import com.naukri.driver.dto.request.user.UserUpdateRequest;
import com.naukri.driver.dto.response.user.UserResponse;
import com.naukri.driver.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toEntity(UserRegistrationRequest request);
    User updateUser(UserUpdateRequest request, @MappingTarget User user);
    UserResponse toResponseDTO(User user);
}
