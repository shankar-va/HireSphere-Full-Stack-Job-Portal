package com.hiresphere.driver.repository;


import com.hiresphere.driver.model.entity.Job;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;


@Repository

public interface JobRepository extends JpaRepository<Job, Integer>, JpaSpecificationExecutor<Job> {


}

