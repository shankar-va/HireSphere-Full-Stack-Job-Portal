package com.hiresphere.driver.controller;


import com.hiresphere.driver.dto.request.company.CompanyRegistrationRequest;

import com.hiresphere.driver.dto.request.company.CompanyUpdateRequest;

import com.hiresphere.driver.dto.response.company.CompanyResponse;

import com.hiresphere.driver.service.impl.CompanyServiceImpl;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController

@RequestMapping("/api/companies")

@RequiredArgsConstructor

public class CompanyController {


    private final CompanyServiceImpl companyService;


    @PostMapping("/")

    public ResponseEntity<CompanyResponse> registerCompany(@RequestBody @Valid CompanyRegistrationRequest request) {


        return ResponseEntity.status(HttpStatus.CREATED)

                             .body(companyService.register(request));

    }


    @GetMapping("/{id}")

    public ResponseEntity<CompanyResponse> getCompany(@PathVariable("id") Integer id) {


        return ResponseEntity.status(HttpStatus.OK)

                             .body(companyService.getCompanyById(id));

    }


    @GetMapping("/")

    public ResponseEntity<List<CompanyResponse>> getCompanies() {


        return ResponseEntity.status(HttpStatus.OK)

                             .body(companyService.getCompanies());

    }


    @PutMapping("/")

    public ResponseEntity<CompanyResponse> updateCompany(@RequestBody @Valid CompanyUpdateRequest request) {


        return ResponseEntity.status(HttpStatus.ACCEPTED)

                             .body(companyService.updateCompany(request));

    }


    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteCompany(@PathVariable("id") Integer id) {


        companyService.deleteCompany(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)

                             .body("Company Deleted Successfully");

    }


}

