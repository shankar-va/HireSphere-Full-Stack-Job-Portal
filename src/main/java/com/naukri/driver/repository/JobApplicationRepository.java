package com.naukri.driver.repository;

import com.naukri.driver.model.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer>, JpaSpecificationExecutor<JobApplication> {
    Boolean existsByJobJobIdAndJobSeekerJobSeekerId(Integer jobId,Integer jobSeekerId);

    List<JobApplication> findByJobSeekerJobSeekerId(Integer id);
}
