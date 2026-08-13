package com.naukri.driver.controller;

import com.naukri.driver.dto.request.resume.ResumeCreateRequest;
import com.naukri.driver.dto.request.resume.ResumeUpdateRequest;
import com.naukri.driver.dto.response.resume.ResumeResponse;
import com.naukri.driver.service.impl.ResumeServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeController {
    private final ResumeServiceImpl resumeService;
    @PostMapping("/register")
    public ResponseEntity<ResumeResponse> register(@RequestBody @Valid ResumeCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(resumeService.createResume(request));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ResumeResponse> get(@PathVariable @NotNull Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(resumeService.getResumeById(id));
    }
    @GetMapping("/get/jobSeeker/{id}")
    public ResponseEntity<ResumeResponse> getByJobSeeker(@PathVariable @NotNull Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(resumeService.getResumeByJobSeekerId(id));
    }
    @PutMapping("/update")
    public ResponseEntity<ResumeResponse> update(@RequestBody @Valid ResumeUpdateRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(resumeService.updateResume(request));
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable @NotNull Integer id){
        resumeService.deleteResume(id);
    }
}
