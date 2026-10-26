package com.hiresphere.driver.controller;


import com.hiresphere.driver.dto.request.jobseeker.JobSeekerRegistrationRequest;

import com.hiresphere.driver.dto.request.jobseeker.JobSeekerSearchRequest;

import com.hiresphere.driver.dto.request.jobseeker.JobSeekerUpdateRequest;

import com.hiresphere.driver.dto.response.jobseeker.JobSeekerResponse;

import com.hiresphere.driver.dto.response.jobseeker.JobSeekerSummaryResponse;

import com.hiresphere.driver.service.impl.JobSeekerServiceImpl;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequiredArgsConstructor

@RestController

@RequestMapping("/api/jobSeeker")

public class JobSeekerController {


    private final JobSeekerServiceImpl jobSeekerService;


    @PostMapping("/{userId}")

    public ResponseEntity<JobSeekerResponse> register(@RequestBody @Valid JobSeekerRegistrationRequest request,

                                                      @PathVariable Integer userId) {


        return ResponseEntity.status(HttpStatus.CREATED)

                             .body(jobSeekerService.registerJobSeeker(request, userId));

    }


    @GetMapping("/{id}")

    public ResponseEntity<JobSeekerResponse> getJobSeeker(@PathVariable Integer id) {


        return ResponseEntity.status(HttpStatus.OK)

                             .body(jobSeekerService.getJobSeekerById(id));

    }


    @GetMapping("/")

    public ResponseEntity<List<JobSeekerResponse>> getJobSeekers() {


        return ResponseEntity.status(HttpStatus.OK)

                             .body(jobSeekerService.getJobSeekers());

    }


    @PutMapping("/")

    public ResponseEntity<JobSeekerResponse> update(@RequestBody @Valid JobSeekerUpdateRequest request) {


        return ResponseEntity.status(HttpStatus.ACCEPTED)

                             .body(jobSeekerService.updateJobSeeker(request));

    }


    @DeleteMapping("/{id}")

    public void delete(@PathVariable Integer id) {


        jobSeekerService.deleteJobSeeker(id);

    }


    @PostMapping("/search")

    public ResponseEntity<Page<JobSeekerSummaryResponse>> search(

            @RequestBody JobSeekerSearchRequest request,

            @RequestParam(defaultValue = "0") Integer page,

            @RequestParam(defaultValue = "1") Integer size,

            @RequestParam(defaultValue = "") String sort,

            @RequestParam(defaultValue = "") String sortDirection

    ) {


        return ResponseEntity.status(HttpStatus.OK)

                             .body(jobSeekerService.searchJobSeekers(request, page, size, sort, sortDirection));

    }


}

