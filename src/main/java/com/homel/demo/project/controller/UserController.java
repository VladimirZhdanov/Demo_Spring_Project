package com.homel.demo.project.controller;

import com.homel.demo.project.dto.UserDTO;
import com.homel.demo.project.mapper.UserMapper;
import com.homel.demo.project.rest.CreateUserRequest;
import com.homel.demo.project.rest.CreateUserResponse;
import com.homel.demo.project.rest.UserRest;
import com.homel.demo.project.service.RoleService;
import com.homel.demo.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public UserRest getUser(@PathVariable long id) {
        return userMapper.rest(userService.getUser(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "50") int limit) {
        return userMapper.rests(userService.getUsers(page, limit));
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
        UserDTO newUser = userService.createNewUser(userMapper.dto(createUserRequest));

        return userMapper.response(newUser);
    }
}
