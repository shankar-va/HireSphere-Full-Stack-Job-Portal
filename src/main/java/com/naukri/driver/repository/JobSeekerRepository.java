package com.naukri.driver.repository;

import com.naukri.driver.model.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobSeekerRepository extends JpaRepository<JobSeeker,Integer>,JpaSpecificationExecutor<JobSeeker> {

	public Boolean existsByUserUserId(Integer userId);
}
