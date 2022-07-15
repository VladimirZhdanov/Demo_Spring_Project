package com.homel.demo.project.mapper;

import com.homel.demo.project.dto.InstructorDetailsDTO;
import com.homel.demo.project.entity.InstructorDetails;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructorDetailsMapper {
    InstructorDetailsDTO dto(InstructorDetails entity);
    List<InstructorDetailsDTO> dtos(List<InstructorDetails> entity);
    InstructorDetails entity(InstructorDetailsDTO dto);
    List<InstructorDetails> entities(List<InstructorDetailsDTO> dto);
}
