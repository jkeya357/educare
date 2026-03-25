package com.pm.userservice.mapper;

import com.pm.userservice.model.entity.User;
import com.pm.userservice.model.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    List<UserResponseDto> toDto(List<User> users);
    UserResponseDto toDto(User user);
}
