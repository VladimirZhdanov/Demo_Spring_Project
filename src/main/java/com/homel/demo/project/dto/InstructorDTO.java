package com.homel.demo.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDTO {
    private long id;
    private String name;
    private InstructorDetailsDTO instructorDetails;
}
