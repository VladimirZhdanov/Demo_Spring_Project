package com.homel.demo.project.service;

import com.homel.demo.project.dto.UserDTO;
import com.homel.demo.project.entity.Course;
import com.homel.demo.project.entity.Instructor;
import org.mapstruct.control.MappingControl;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDTO save(UserDTO userDTO);
    UserDTO getUser(String email);
    UserDTO getUser(long id);
    UserDTO updateUser(UserDTO userDTO);
    void deleteUser(long id);
    List<UserDTO> getUsers(int page, int limit);
}
