package com.naukri.driver.service.impl;

import org.springframework.stereotype.Service;

import com.naukri.driver.dto.request.company.CompanyRegistrationRequest;
import com.naukri.driver.dto.response.company.CompanyResponse;
import com.naukri.driver.dto.request.company.CompanyUpdateRequest;
import com.naukri.driver.exception.customExceptions.company.CompanyEmailExistsException;
import com.naukri.driver.exception.customExceptions.company.CompanyNotFoundException;
import com.naukri.driver.exception.customExceptions.company.CompanyPhoneNumberExistsException;
import com.naukri.driver.mapper.company.CompanyMapper;
import com.naukri.driver.model.entity.Company;
import com.naukri.driver.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl  {
	private final CompanyRepository repository;
	private final CompanyMapper companyMapper;


	public CompanyResponse register(CompanyRegistrationRequest registrationRequest) {
		Company company = companyMapper.toEntity(registrationRequest);
		if (repository.existsByEmail(company.getEmail()))
			throw new CompanyEmailExistsException("Email already exists!!!...");
		if (repository.existsByPhoneNumber(company.getPhoneNumber()))
			throw new CompanyPhoneNumberExistsException("Phone Number already exists!!!...");
		Company newCompany = repository.save(company);
		return companyMapper.toResponseDTO(newCompany);
	}

	public CompanyResponse getCompanyById(Integer id) {
		Company company = repository.findById(id).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
		return companyMapper.toResponseDTO(company);
	}
	public List<CompanyResponse> getCompanies(){
		return repository.findAll().stream().map(companyMapper::toResponseDTO).collect(Collectors.toList());
	}

	public CompanyResponse updateCompany(CompanyUpdateRequest request) {
		Company company = repository.findById(request.getCompanyId()).orElseThrow(()->new CompanyNotFoundException("Company Not Found"));
		if(request.getEmail()!=null)
			if(repository.existsByEmailAndCompanyIdNot(request.getEmail(), request.getCompanyId()))throw new CompanyEmailExistsException("Email already exists!!!...");
		if(request.getPhoneNumber()!=null)
			if(repository.existsByPhoneNumberAndCompanyIdNot(request.getPhoneNumber(), request.getCompanyId()))throw new CompanyPhoneNumberExistsException("Phone Number already exists!!!...");
		Company result = companyMapper.toUpdateEntity(request, company);
		repository.save(result);
		return companyMapper.toResponseDTO(result);
	}
	public void deleteCompany(Integer id) {
		if(!repository.existsById(id))throw new CompanyNotFoundException("Company Not Found");
		repository.deleteById(id);
	}
}
