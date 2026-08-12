package com.naukri.driver.controller;

import com.naukri.driver.dto.request.recruiter.RecruiterRegistrationRequest;
import com.naukri.driver.dto.request.recruiter.RecruiterUpdateRequest;
import com.naukri.driver.dto.response.recruiter.RecruiterResponse;
import com.naukri.driver.service.impl.RecruiterServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/recruiter")
public class RecruiterController {
    RecruiterServiceImpl recruiterService;
    @PostMapping("/register")
    public ResponseEntity<RecruiterResponse> register(@RequestBody @Valid RecruiterRegistrationRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(recruiterService.register(request));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<RecruiterResponse> get(@PathVariable @NotNull Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(recruiterService.getRecruiterById(id));
    }
    @PutMapping("/update")
    public ResponseEntity<RecruiterResponse> update(@RequestBody @Valid RecruiterUpdateRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(recruiterService.updateRecruiter(request));
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable @NotNull Integer id){
        recruiterService.deleteRecruiter(id);
    }
}
