package com.hiresphere.driver.controller;


import com.hiresphere.driver.dto.request.user.UserUpdateRequest;
import com.hiresphere.driver.dto.response.user.UserResponse;
import com.hiresphere.driver.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor

@RestController

@RequestMapping("/api/user")

public class UserController {


    private final UserServiceImpl userService;


    @GetMapping("/")

    public ResponseEntity<List<UserResponse>> getUsers() {


        return ResponseEntity.status(HttpStatus.OK)

                             .body(userService.getUsers());

    }


    @GetMapping("/{id}")

    public ResponseEntity<UserResponse> getUser(@PathVariable Integer id) {


        return ResponseEntity.status(HttpStatus.OK)

                             .body(userService.getUserById(id));

    }


    @PatchMapping("/")

    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserUpdateRequest request) {


        return ResponseEntity.status(HttpStatus.ACCEPTED)

                             .body(userService.updateUser(request));

    }


    @DeleteMapping("/{id}")

    public void delete(@PathVariable Integer id) {


        userService.deleteUser(id);

    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication){
        return ResponseEntity.ok(userService.getUserByEmail(authentication.getName()));
    }

}

