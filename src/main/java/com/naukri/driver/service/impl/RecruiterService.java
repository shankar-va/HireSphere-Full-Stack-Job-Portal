package com.naukri.driver.service.impl;

import com.naukri.driver.dto.request.recruiter.RecruiterRegistrationRequest;
import com.naukri.driver.dto.request.recruiter.RecruiterUpdateRequest;
import com.naukri.driver.dto.response.recruiter.RecruiterResponse;
import com.naukri.driver.exception.customExceptions.recruiter.RecruiterNotFound;
import com.naukri.driver.mapper.recruiter.RecruiterMapper;
import com.naukri.driver.model.entity.Company;
import com.naukri.driver.model.entity.Recruiter;
import com.naukri.driver.model.entity.User;
import com.naukri.driver.repository.CompanyRepository;
import com.naukri.driver.repository.RecruiterRepository;
import com.naukri.driver.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruiterService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final RecruiterMapper recruiterMapper;
    private final RecruiterRepository recruiterRepository;


    public RecruiterResponse register(RecruiterRegistrationRequest request) {
        User user = userRepository.findById(request.getUserId())
                                  .orElseThrow(() -> new RuntimeException("Invalid userID"));
        Company company = companyRepository.findById(request.getCompanyId())
                                           .orElseThrow(() -> new RuntimeException("Invalid companyID"));
        Recruiter recruiter = recruiterMapper.toEntity(request, user, company);
        Recruiter recruiter1 = recruiterRepository.save(recruiter);
        return recruiterMapper.toResponseDTO(recruiter1);
    }
    public RecruiterResponse getRecruiterById(Integer id) {
    	Recruiter recruiter = recruiterRepository.findById(id).orElseThrow(()->new RecruiterNotFound("Recruiter Not Found!!!..."));
    	return recruiterMapper.toResponseDTO(recruiter);
    }
    
    public RecruiterResponse updateRecruiter(RecruiterUpdateRequest request) {
    	Recruiter recruiter = recruiterRepository.findById(request.getRecruiterId()).orElseThrow(()->new RecruiterNotFound("Recruiter Not Found!!!..."));
    	Recruiter recruiter2 = recruiterMapper.toUpdateRecruiter(request, recruiter);
    	return recruiterMapper.toResponseDTO(recruiter2);
    }

}
