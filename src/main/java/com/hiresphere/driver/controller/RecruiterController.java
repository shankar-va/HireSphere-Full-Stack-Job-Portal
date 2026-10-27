package com.hiresphere.driver.controller;


import com.hiresphere.driver.dto.request.recruiter.RecruiterRegistrationRequest;

import com.hiresphere.driver.dto.request.recruiter.RecruiterUpdateRequest;

import com.hiresphere.driver.dto.response.recruiter.RecruiterResponse;

import com.hiresphere.driver.service.impl.RecruiterServiceImpl;

import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController

@RequiredArgsConstructor

@RequestMapping("api/recruiter")


public class RecruiterController {


    private final RecruiterServiceImpl recruiterService;


    @PostMapping("/")

    public ResponseEntity<RecruiterResponse> register(@RequestBody @Valid RecruiterRegistrationRequest request) {


        return ResponseEntity.status(HttpStatus.CREATED)

                             .body(recruiterService.register(request));

    }


    @GetMapping("/{id}")

    @PreAuthorize("hasRole('RECRUITER')")

    public ResponseEntity<RecruiterResponse> get(@PathVariable @NotNull Integer id) {


        return ResponseEntity.status(HttpStatus.OK)

                             .body(recruiterService.getRecruiterById(id));

    }


    @PutMapping("/")

    @PreAuthorize("hasRole('RECRUITER')")

    public ResponseEntity<RecruiterResponse> update(@RequestBody @Valid RecruiterUpdateRequest request) {


        return ResponseEntity.status(HttpStatus.ACCEPTED)

                             .body(recruiterService.updateRecruiter(request));

    }


    @DeleteMapping("/{id}")

    @PreAuthorize("hasRole('RECRUITER')")

    public void delete(@PathVariable @NotNull Integer id) {


        recruiterService.deleteRecruiter(id);

    }


}

