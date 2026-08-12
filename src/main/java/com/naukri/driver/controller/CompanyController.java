package com.naukri.driver.controller;

import com.naukri.driver.dto.request.company.CompanyRegistrationRequest;
import com.naukri.driver.dto.response.company.CompanyResponse;
import com.naukri.driver.service.impl.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyServiceImpl companyService;
    @PostMapping("/companies")
    public ResponseEntity<CompanyResponse> registerCompany(@RequestBody CompanyRegistrationRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.register(request));
    }
}
