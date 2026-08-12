package com.naukri.driver.controller;

import com.naukri.driver.dto.request.job.JobCreateRequest;
import com.naukri.driver.dto.request.job.JobSearchRequest;
import com.naukri.driver.dto.request.job.JobUpdateRequest;
import com.naukri.driver.dto.response.job.JobResponse;
import com.naukri.driver.dto.response.job.JobSummaryResponse;
import com.naukri.driver.service.impl.JobServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JobController {
    private final JobServiceImpl jobService;
    @PostMapping("/register")
    public ResponseEntity<JobResponse> register(@RequestBody @Valid JobCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(request));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<JobResponse> getJob(@PathVariable @NotNull Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getJobById(id));
    }
    @GetMapping("/get/search")
    public ResponseEntity<Page<JobSummaryResponse>> searchJob(@RequestBody @Valid JobSearchRequest request,
                                                              @RequestParam Integer page,
                                                              @RequestParam Integer size,
                                                              @RequestParam String sort,
                                                              @RequestParam String direction){
        return ResponseEntity.status(HttpStatus.OK).body(jobService.searchJobs(page,size,sort,direction,request));
    }
    @PatchMapping("/job")
    public ResponseEntity<JobResponse> update(@RequestBody @Valid JobUpdateRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(jobService.updateJob(request));
    }
    @DeleteMapping("/job")
    public void delete(@PathVariable @NotNull Integer id){
        jobService.deleteJob(id);
    }
    @DeleteMapping("/job/close")
    public ResponseEntity<JobSummaryResponse> close(@PathVariable @NotNull Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(jobService.closeJob(id));

    }
}
