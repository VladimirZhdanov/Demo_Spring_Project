package com.homel.demo.project.mapper;

import com.homel.demo.project.dto.CourseDTO;
import com.homel.demo.project.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CourseMapperTest {
    private CourseMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CourseMapperImpl();
    }

    @Test
    public void shouldMapCourseToDto() {
        Course entity = new Course();
        entity.setName("Math");

        //when
        CourseDTO dto = mapper.dto(entity);

        //then
        assertNotNull(dto);
        assertEquals(0, dto.getId());
        assertEquals("Math", dto.getName());
    }

    @Test
    public void shouldMapCourseDTOToEntity() {
        CourseDTO dto = new CourseDTO();
        dto.setName("Java");

        //when
        Course entity = mapper.entity(dto);

        //then
        assertNotNull(entity);
        assertEquals(0, entity.getId());
        assertEquals("Java", entity.getName());
    }
}