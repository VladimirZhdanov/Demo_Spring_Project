package com.homel.demo.project.mapper;

import com.homel.demo.project.dto.RoleDTO;
import com.homel.demo.project.dto.UserDTO;
import com.homel.demo.project.entity.RoleEntity;
import com.homel.demo.project.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO dto(RoleEntity entity);
    List<RoleDTO> dtos(List<RoleEntity> entities);
    RoleEntity entity(RoleDTO dto);
    List<RoleEntity> entities(List<RoleDTO> dtos);
}
