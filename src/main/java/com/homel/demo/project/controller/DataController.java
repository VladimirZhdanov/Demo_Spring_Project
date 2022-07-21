package com.homel.demo.project.controller;

import com.homel.demo.project.dto.CourseDTO;
import com.homel.demo.project.dto.InstructorDTO;
import com.homel.demo.project.mapper.CourseMapper;
import com.homel.demo.project.mapper.InstructorMapper;
import com.homel.demo.project.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("data/v1")
@AllArgsConstructor
public class DataController {

    private DataService dataService;
    private InstructorMapper instructorMapper;
    private CourseMapper courseMapper;

    @GetMapping(path = "/instructors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public InstructorDTO getInstructor(@PathVariable long id) {
        return instructorMapper.dto(dataService.getInstructor(id));
    }

    @GetMapping(path = "/instructors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InstructorDTO> getInstructors() {
        return instructorMapper.dtos(dataService.getAllInstructors());
    }

    @GetMapping(path = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDTO> getCourses() {
        return courseMapper.dtos(dataService.getAllCourses());
    }
}
