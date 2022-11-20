package com.homel.demo.project.controller;

import com.homel.demo.project.integration.UsersFeignClient;
import com.homel.demo.project.rest.UserRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("feign")
public class UserFeignController {

    private final UsersFeignClient usersFeignClient;

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserRest> getUsers(@RequestHeader(value = "Authorization") String authorizationHeader) {
        return usersFeignClient.getAllUsers(authorizationHeader);
    }
}
