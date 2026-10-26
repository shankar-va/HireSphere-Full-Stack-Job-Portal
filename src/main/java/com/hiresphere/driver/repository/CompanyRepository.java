package com.hiresphere.driver.repository;


import com.hiresphere.driver.model.entity.Company;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository

public interface CompanyRepository extends JpaRepository<Company, Integer> {


    Boolean existsByEmail(String email);


    Boolean existsByPhoneNumber(String phoneNumber);


    Boolean existsByEmailAndCompanyIdNot(String email, Integer companyId);


    Boolean existsByPhoneNumberAndCompanyIdNot(String phoneNumber, Integer companyId);


}

