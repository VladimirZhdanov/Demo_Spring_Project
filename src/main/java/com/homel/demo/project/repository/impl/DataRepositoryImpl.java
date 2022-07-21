package com.homel.demo.project.repository.impl;

import com.homel.demo.project.entity.Course;
import com.homel.demo.project.entity.Instructor;
import com.homel.demo.project.repository.DataRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@AllArgsConstructor
@Repository
public class DataRepositoryImpl implements DataRepository {

    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    @Transactional
    public Instructor getInstructor(long id) {
        return entityManager.find(Instructor.class, id);
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
        Instructor instructor = new Instructor();
        instructor.setId(id);

        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public List<Instructor> getAllInstructors() {
        TypedQuery<Instructor> query = entityManager.createQuery("from Instructor", Instructor.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Course> getAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery("from Course", Course.class);

        return query.getResultList();
    }
}
