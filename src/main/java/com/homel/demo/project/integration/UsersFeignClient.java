package com.homel.demo.project.integration;

import com.homel.demo.project.rest.UserRest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(
        name = "localhost",
        url = "http://localhost:8080",
        configuration = {ClientConfig.class}
)
public interface UsersFeignClient {
    @GetMapping("/users")
    List<UserRest> getAllUsers(@RequestHeader(value = "Authorization") String authorizationHeader);
}
