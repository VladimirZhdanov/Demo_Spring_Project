package com.homel.demo.project.mapper;

import com.homel.demo.project.dto.InstructorDTO;
import com.homel.demo.project.entity.Instructor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructorMapper {
    InstructorDTO dto(Instructor entity);
    List<InstructorDTO> dtos(List<Instructor> entity);
    Instructor entity(InstructorDTO dto);
    List<Instructor> entities(List<InstructorDTO> dto);
}
