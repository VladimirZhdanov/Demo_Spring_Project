package com.homel.demo.project.mapper;

import com.homel.demo.project.dto.AuthorityDTO;
import com.homel.demo.project.entity.AuthorityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
    AuthorityDTO dto(AuthorityEntity entity);
    List<AuthorityDTO> dtos(List<AuthorityEntity> entities);
    AuthorityEntity entity(AuthorityDTO dto);
    List<AuthorityEntity> entities(List<AuthorityDTO> dtos);
}
