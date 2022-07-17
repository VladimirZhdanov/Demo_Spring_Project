package com.homel.demo.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDTO {
    private long id;
    private String name;

    public AuthorityDTO(String name) {
        this.name = name;
    }
}