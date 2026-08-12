package com.naukri.driver.controller;

import com.naukri.driver.dto.request.company.CompanyRegistrationRequest;
import com.naukri.driver.dto.request.company.CompanyUpdateRequest;
import com.naukri.driver.dto.response.company.CompanyResponse;
import com.naukri.driver.service.impl.CompanyServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyServiceImpl companyService;
    @PostMapping("/companies")
    public ResponseEntity<CompanyResponse> registerCompany(@RequestBody @Valid CompanyRegistrationRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.register(request));
    }
    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyResponse> getCompany(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompanyById(id));
    }
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyResponse>> getCompanies(){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompanies());
    }
    @PutMapping("/companies")
    public ResponseEntity<CompanyResponse> updateCompany(@RequestBody @Valid CompanyUpdateRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(companyService.updateCompany(request));
    }
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Integer id){
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Company Deleted Successfully");
    }
}
