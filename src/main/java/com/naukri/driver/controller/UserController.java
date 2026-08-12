package com.naukri.driver.controller;

import com.naukri.driver.dto.request.user.UserRegistrationRequest;
import com.naukri.driver.dto.request.user.UserUpdateRequest;
import com.naukri.driver.dto.response.user.UserResponse;
import com.naukri.driver.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserServiceImpl userService;
    @GetMapping("/user")
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }
    @PostMapping("/user")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRegistrationRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request));
    }
    @PatchMapping("/user")
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserUpdateRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(request));
    }
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}
