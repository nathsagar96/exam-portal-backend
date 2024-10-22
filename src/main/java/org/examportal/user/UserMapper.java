package org.examportal.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toEntity(UserRequestDto userRequestDto);

  UserResponseDto toDto(User user);
}
