package com.naukri.driver.repository;

import com.naukri.driver.model.entity.JobApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer>, JpaSpecificationExecutor<JobApplication> {
    public Boolean existsByJobJobIdAndJobSeekerJobSeekerId(Integer jobId,Integer jobSeekerId);
}
