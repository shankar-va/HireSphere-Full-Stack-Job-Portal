package com.naukri.driver.service.impl;

import com.naukri.driver.dto.request.resume.ResumeCreateRequest;
import com.naukri.driver.dto.request.resume.ResumeUpdateRequest;
import com.naukri.driver.dto.response.resume.ResumeResponse;
import com.naukri.driver.exception.customExceptions.jobSeeker.JobSeekerNotFoundException;
import com.naukri.driver.exception.customExceptions.resume.ResumeAlreadyExistsException;
import com.naukri.driver.exception.customExceptions.resume.ResumeNotFoundException;
import com.naukri.driver.mapper.resume.ResumeMapper;
import com.naukri.driver.model.entity.JobSeeker;
import com.naukri.driver.model.entity.Resume;
import com.naukri.driver.repository.JobSeekerRepository;
import com.naukri.driver.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl {

    private final ResumeRepository resumeRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final ResumeMapper resumeMapper;


    @Transactional
    public ResumeResponse createResume(
            ResumeCreateRequest request) {

        JobSeeker jobSeeker =
                jobSeekerRepository.findById(
                        request.getJobSeekerId()
                ).orElseThrow(() ->
                        new JobSeekerNotFoundException(
                                "Job Seeker Not Found"
                        )
                );

        if (resumeRepository.existsByJobSeekerJobSeekerId(
                request.getJobSeekerId())) {

            throw new ResumeAlreadyExistsException(
                    "Job Seeker already has a Resume"
            );
        }

        Resume resume =
                resumeMapper.toEntity(
                        request,
                        jobSeeker
                );

        Resume savedResume =
                resumeRepository.save(resume);

        return resumeMapper.toResponseDTO(savedResume);
    }


    @Transactional(readOnly = true)
    public ResumeResponse getResumeById(Integer resumeId) {

        Resume resume =
                resumeRepository.findById(resumeId)
                                .orElseThrow(() ->
                                        new ResumeNotFoundException(
                                                "Resume Not Found"
                                        )
                                );

        return resumeMapper.toResponseDTO(resume);
    }


    @Transactional(readOnly = true)
    public ResumeResponse getResumeByJobSeekerId(
            Integer jobSeekerId) {

        Resume resume =
                resumeRepository
                        .findByJobSeekerJobSeekerId(jobSeekerId)
                        .orElseThrow(() ->
                                new ResumeNotFoundException(
                                        "Resume Not Found"
                                )
                        );

        return resumeMapper.toResponseDTO(resume);
    }


    @Transactional
    public ResumeResponse updateResume(
            ResumeUpdateRequest request) {

        Resume resume =
                resumeRepository.findById(
                        request.getResumeId()
                ).orElseThrow(() ->
                        new ResumeNotFoundException(
                                "Resume Not Found"
                        )
                );

        Resume updatedResume =
                resumeMapper.updateResume(
                        request,
                        resume
                );

        Resume savedResume =
                resumeRepository.save(updatedResume);

        return resumeMapper.toResponseDTO(savedResume);
    }


    @Transactional
    public void deleteResume(Integer resumeId) {

        Resume resume =
                resumeRepository.findById(resumeId)
                                .orElseThrow(() ->
                                        new ResumeNotFoundException(
                                                "Resume Not Found"
                                        )
                                );

        resumeRepository.delete(resume);
    }
}