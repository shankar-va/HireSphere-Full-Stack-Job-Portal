package com.naukri.driver.controller;

import com.naukri.driver.dto.request.jobApplication.ApplicationStatusUpdateRequest;
import com.naukri.driver.dto.request.jobApplication.ApplyForJobRequest;
import com.naukri.driver.dto.request.jobApplication.JobApplicationSearchRequest;
import com.naukri.driver.dto.response.jobApplication.JobApplicationResponse;
import com.naukri.driver.dto.response.jobApplication.JobApplicationSummaryResponse;
import com.naukri.driver.service.impl.JobApplicationServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobApplication")
public class JobApplicationController {
    private final JobApplicationServiceImpl jobApplicationService;
    @PostMapping("/apply")
    public ResponseEntity<JobApplicationResponse> apply(@RequestBody @Valid ApplyForJobRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(jobApplicationService.applyForJob(request));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<JobApplicationResponse> getApplication(@PathVariable @NotNull Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.getApplicationById(id));
    }
    @GetMapping("/get/applied/{id}")
    public ResponseEntity<List<JobApplicationSummaryResponse>> getApplications(@PathVariable @NotNull Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.getApplications(id));
    }
    @GetMapping("/get/search")
    public ResponseEntity<Page<JobApplicationSummaryResponse>> search(@RequestBody @Valid JobApplicationSearchRequest request,
                                                                      @RequestParam(defaultValue = "0") Integer page,
                                                                      @RequestParam(defaultValue = "1") Integer size,
                                                                      @RequestParam String sort,
                                                                      @RequestParam String direction){
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.searchApplications(request,page,size,sort,direction));
    }
    @PutMapping("/update")
    public ResponseEntity<JobApplicationResponse> update(ApplicationStatusUpdateRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(jobApplicationService.updateApplicationStatus(request));
    }
    @PatchMapping("/withdraw/{id}")
    public  ResponseEntity<JobApplicationResponse> withdraw(@PathVariable @NotNull Integer id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(jobApplicationService.withdrawApplication(id));
    }
}
