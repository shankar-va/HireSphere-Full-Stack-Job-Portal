package com.naukri.driver.service.impl;

import com.naukri.driver.dto.request.recruiter.RecruiterRegistrationRequest;
import com.naukri.driver.dto.response.recruiter.RecruiterResponse;
import com.naukri.driver.mapper.recruiter.RecruiterMapper;
import com.naukri.driver.model.entity.Company;
import com.naukri.driver.model.entity.Recruiter;
import com.naukri.driver.model.entity.User;
import com.naukri.driver.repository.CompanyRepository;
import com.naukri.driver.repository.RecruiterRepository;
import com.naukri.driver.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RecruiterService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final RecruiterMapper recruiterMapper;
    private final RecruiterRepository recruiterRepository;

    public RecruiterService(UserRepository userRepository, CompanyRepository companyRepository, RecruiterMapper recruiterMapper, RecruiterRepository recruiterRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.recruiterMapper = recruiterMapper;
        this.recruiterRepository = recruiterRepository;
    }

    public RecruiterResponse register(RecruiterRegistrationRequest request) {
        User user = userRepository.findById(request.getUserId())
                                  .orElseThrow(() -> new RuntimeException("Invalid userID"));
        Company company = companyRepository.findById(request.getCompanyId())
                                           .orElseThrow(() -> new RuntimeException("Invalid companyID"));
        Recruiter recruiter = recruiterMapper.toEntity(request, user, company);
        Recruiter recruiter1 = recruiterRepository.save(recruiter);
        return recruiterMapper.toResponseDTO(recruiter1);
    }

}
