package com.pm.userservice.mapper;

import com.pm.userservice.model.entity.User;
import com.pm.userservice.model.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponseDto toDto(User user);
}
