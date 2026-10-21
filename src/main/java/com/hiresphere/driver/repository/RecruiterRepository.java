package com.hiresphere.driver.repository;


import com.hiresphere.driver.model.entity.Recruiter;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository

public interface RecruiterRepository extends JpaRepository<Recruiter, Integer> {


    Boolean existsByUserUserId(Integer userId);



    Boolean existsByEmployeeCode(String employeeCode);



    Boolean existsByEmployeeCodeAndRecruiterIdNot(String emolyeeCode, Integer recruiterId);


}

