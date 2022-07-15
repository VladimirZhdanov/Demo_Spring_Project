package com.homel.demo.project.repository;

import com.homel.demo.project.entity.Course;
import com.homel.demo.project.entity.Instructor;

import java.util.List;

public interface DataRepository {
    void save(Instructor instructor);
    Instructor getInstructor(long id);
    void update(Instructor instructor);
    void deleteStudent(long id);
    List<Instructor> getAllInstructors();
    List<Course> getAllCourses();
}
