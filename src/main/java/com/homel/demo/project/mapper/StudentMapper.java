package com.homel.demo.project.mapper;

import com.homel.demo.project.dto.StudentDTO;
import com.homel.demo.project.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO dto(Student entity);
    List<StudentDTO> dtos(List<Student> entity);
    Student entity(StudentDTO dto);
    List<Student> entities(List<StudentDTO> dto);
}
