package com.homel.demo.project.controller;

import com.homel.demo.project.dto.CourseDTO;
import com.homel.demo.project.dto.InstructorDTO;
import com.homel.demo.project.entity.*;
import com.homel.demo.project.mapper.CourseMapper;
import com.homel.demo.project.mapper.InstructorMapper;
import com.homel.demo.project.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("data/v1")
@AllArgsConstructor
public class DataController {

    private DataService dataService;
    private InstructorMapper instructorMapper;
    private CourseMapper courseMapper;

    @GetMapping(path = "/init")
    public void init() {
        Instructor instructor = new Instructor();
        Student student1 = new Student(0, "Doc", null);
        Student student2 = new Student(0, "Noob", null);
        Student student3 = new Student(0, "Pro", null);
        Review review = new Review(0, "It's cool, man");
        Course course1 = new Course(0,"Math", instructor, List.of(review), List.of(student1));
        Course course2 = new Course(0,"Java", instructor, null, List.of(student2, student3));
        InstructorDetails instructorDetails = new InstructorDetails(0, "Play WoW");
        instructor.setName("Tom");
        instructor.setInstructorDetails(instructorDetails);
        instructor.setCourses(Arrays.asList(course1, course2));
        dataService.save(instructor);
    }

    @GetMapping(path = "/instructors/{id}", produces = "application/json")
    public InstructorDTO getInstructor(@PathVariable long id) {
        return instructorMapper.dto(dataService.getInstructor(id));
    }

    @GetMapping(path = "/instructors", produces = "application/json")
    public List<InstructorDTO> getInstructors() {
        return instructorMapper.dtos(dataService.getAllInstructors());
    }

    @GetMapping(path = "/courses", produces = "application/json")
    public List<CourseDTO> getCourses() {
        return courseMapper.dtos(dataService.getAllCourses());
    }


}
