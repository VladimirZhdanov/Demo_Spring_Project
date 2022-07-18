package com.homel.demo.project.service.impl;

import com.homel.demo.project.entity.Course;
import com.homel.demo.project.entity.Instructor;
import com.homel.demo.project.repository.DataRepository;
import com.homel.demo.project.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class DataServiceImpl implements DataService {
    private DataRepository dataRepository;

    @Override
    @Transactional
    public void save(Instructor instructor) {
        dataRepository.save(instructor);
    }

    @Override
    @Transactional
    public Instructor getInstructor(long id) {
        return dataRepository.getInstructor(id);
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        dataRepository.update(instructor);
    }

    @Override
    @Transactional
    public void deleteStudent(long id) {
        dataRepository.deleteStudent(id);
    }

    @Override
    @Transactional
    public List<Instructor> getAllInstructors() {
        return dataRepository.getAllInstructors();
    }

    @Override
    @Transactional
    public List<Course> getAllCourses() {
        return dataRepository.getAllCourses();
    }
}
