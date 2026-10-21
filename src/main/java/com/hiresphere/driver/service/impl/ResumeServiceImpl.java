package com.hiresphere.driver.service.impl;


import com.hiresphere.driver.dto.request.resume.ResumeCreateRequest;

import com.hiresphere.driver.dto.request.resume.ResumeUpdateRequest;

import com.hiresphere.driver.dto.response.resume.ResumeResponse;

import com.hiresphere.driver.exception.customExceptions.jobSeeker.JobSeekerNotFoundException;

import com.hiresphere.driver.exception.customExceptions.resume.ResumeAlreadyExistsException;

import com.hiresphere.driver.exception.customExceptions.resume.ResumeNotFoundException;

import com.hiresphere.driver.mapper.resume.ResumeMapper;

import com.hiresphere.driver.model.entity.JobSeeker;

import com.hiresphere.driver.model.entity.Resume;

import com.hiresphere.driver.repository.JobSeekerRepository;

import com.hiresphere.driver.repository.ResumeRepository;

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

                                   )

                                   .orElseThrow(() ->

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

                                )

                                .orElseThrow(() ->

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
