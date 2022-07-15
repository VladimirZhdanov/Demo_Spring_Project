package com.homel.demo.project.controller;

import com.homel.demo.project.dto.UserDTO;
import com.homel.demo.project.rest.CreateUserRequest;
import com.homel.demo.project.rest.CreateUserResponse;
import com.homel.demo.project.mapper.UserMapper;
import com.homel.demo.project.rest.UserRest;
import com.homel.demo.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private UserMapper userMapper;
    private UserService userService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public UserRest getUser(@PathVariable long id) {
        return userMapper.rest(userService.getUser(id));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserRest updateUser(@RequestBody CreateUserRequest userDetails) {
        UserDTO user = userService.updateUser(userMapper.dto(userDetails));

        return userMapper.rest(user);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreateUserResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        UserDTO newUser = userService.save(userMapper.dto(createUserRequest));

        return userMapper.response(newUser);
    }
}
