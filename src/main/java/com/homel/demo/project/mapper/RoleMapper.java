package com.homel.demo.project.mapper;

import com.homel.demo.project.dto.RoleDTO;
import com.homel.demo.project.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO dto(RoleEntity entity);
    List<RoleDTO> dtos(List<RoleEntity> entities);
    RoleEntity entity(RoleDTO dto);
    List<RoleEntity> entities(List<RoleDTO> dtos);
}
