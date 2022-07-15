package com.homel.demo.project.mapper;

import com.homel.demo.project.dto.UserDTO;
import com.homel.demo.project.entity.UserEntity;
import com.homel.demo.project.rest.CreateUserRequest;
import com.homel.demo.project.rest.CreateUserResponse;
import com.homel.demo.project.rest.UserRest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO dto(UserEntity entity);
    List<UserDTO> dtos(List<UserEntity> entity);
    UserEntity entity(UserDTO dto);
    List<UserEntity> entities(List<UserDTO> dto);

    UserDTO dto(CreateUserRequest userRequest);

    CreateUserResponse response(UserDTO newUser);

    UserRest rest(UserDTO dto);
}
