package com.homel.demo.project.dto;

import com.homel.demo.project.entity.AuthorityEntity;
import com.homel.demo.project.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private long id;
    private String name;
    private Collection<AuthorityDTO> authorities = new ArrayList<>();

    public RoleDTO(String name, Collection<AuthorityDTO> authorities) {
        this.name = name;
        this.authorities.addAll(authorities);
    }
}