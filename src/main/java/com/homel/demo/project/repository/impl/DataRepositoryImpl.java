package com.homel.demo.project.repository.impl;

import com.homel.demo.project.entity.Course;
import com.homel.demo.project.entity.Instructor;
import com.homel.demo.project.repository.DataRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
@Repository
public class DataRepositoryImpl implements DataRepository {

    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Instructor instructor) {
        Session session = entityManager.unwrap(Session.class);
        session.save(instructor);
    }

    @Override
    @Transactional
    public Instructor getInstructor(long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.load(Instructor.class, id);
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(instructor);
    }

    @Override
    @Transactional
    public void deleteStudent(long id) {
        Session session = entityManager.unwrap(Session.class);
        Instructor instructor = new Instructor();
        instructor.setId(id);
        session.delete(instructor);
    }

    @Override
    @Transactional
    public List<Instructor> getAllInstructors() {
        Session session = entityManager.unwrap(Session.class);
        Query<Instructor> query = session.createQuery("from Instructor", Instructor.class);

        return query.list();
    }

    @Override
    @Transactional
    public List<Course> getAllCourses() {
        Session session = entityManager.unwrap(Session.class);
        Query<Course> query = session.createQuery("from Course", Course.class);

        return query.list();
    }
}
