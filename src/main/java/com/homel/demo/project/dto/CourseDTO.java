package com.homel.demo.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private long id;
    private String name;
    private InstructorDTO instructor;
    private List<ReviewDTO> reviews;
    private List<StudentDTO> students;
}
