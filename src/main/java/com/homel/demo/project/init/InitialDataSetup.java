package com.homel.demo.project.init;

import com.homel.demo.project.entity.*;
import com.homel.demo.project.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class InitialDataSetup {
    private DataService dataService;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
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
}
