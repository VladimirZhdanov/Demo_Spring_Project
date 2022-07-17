package com.homel.demo.project.dto;

import com.homel.demo.project.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private boolean emailVerificationStatus;
    private Collection<RoleDTO> roles = new ArrayList<>();
}
