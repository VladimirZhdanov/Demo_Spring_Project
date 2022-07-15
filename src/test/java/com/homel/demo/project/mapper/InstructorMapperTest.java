package com.homel.demo.project.mapper;

import com.homel.demo.project.dto.InstructorDTO;
import com.homel.demo.project.entity.Course;
import com.homel.demo.project.entity.Instructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InstructorMapperTest {
    private InstructorMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new InstructorMapperImpl();
    }

    @Test
    public void shouldMapInstructorToDto() {
        Instructor entity = new Instructor();
        Course course1 = new Course(0,"Math", entity, null, null);
        Course course2 = new Course(0,"Java", entity, null, null);
        entity.setName("Tom");
        entity.setCourses(Arrays.asList(course1, course2));

        //when
        InstructorDTO dto = mapper.dto(entity);

        //then
        assertNotNull(dto);
        assertEquals(0, dto.getId());
        assertEquals("Tom", dto.getName());
    }

    @Test
    public void shouldMapInstructorDTOToEntity() {
        InstructorDTO dto = new InstructorDTO();
        dto.setName("Tom");

        //when
        Instructor entity = mapper.entity(dto);

        //then
        assertNotNull(entity);
        assertEquals(0, entity.getId());
        assertEquals("Tom", entity.getName());
    }
}