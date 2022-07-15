package com.homel.demo.project.service;

import com.homel.demo.project.entity.Course;
import com.homel.demo.project.entity.Instructor;

import java.util.List;

public interface DataService {
    void save(Instructor instructor);
    Instructor getInstructor(long id);
    void update(Instructor instructor);
    void deleteStudent(long id);
    List<Instructor> getAllInstructors();
    List<Course> getAllCourses();
}
