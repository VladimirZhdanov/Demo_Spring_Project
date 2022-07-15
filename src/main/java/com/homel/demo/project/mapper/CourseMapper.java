package com.homel.demo.project.mapper;

import com.homel.demo.project.dto.CourseDTO;
import com.homel.demo.project.entity.Course;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO dto(Course entity);
    List<CourseDTO> dtos(List<Course> entity);
    Course entity(CourseDTO dto);
    List<Course> entities(List<CourseDTO> dto);
}
