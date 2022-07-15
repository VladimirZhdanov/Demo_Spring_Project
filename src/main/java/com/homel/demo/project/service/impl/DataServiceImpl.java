package com.homel.demo.project.service.impl;

import com.homel.demo.project.entity.Course;
import com.homel.demo.project.entity.Instructor;
import com.homel.demo.project.repository.DataRepository;
import com.homel.demo.project.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DataServiceImpl implements DataService {
    private DataRepository dataRepository;

    @Override
    public void save(Instructor instructor) {
        dataRepository.save(instructor);
    }

    @Override
    public Instructor getInstructor(long id) {
        return dataRepository.getInstructor(id);
    }

    @Override
    public void update(Instructor instructor) {
        dataRepository.update(instructor);
    }

    @Override
    public void deleteStudent(long id) {
        dataRepository.deleteStudent(id);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return dataRepository.getAllInstructors();
    }

    @Override
    public List<Course> getAllCourses() {
        return dataRepository.getAllCourses();
    }
}
