package com.naukri.driver.repository;

import com.naukri.driver.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer>{
	public Boolean existsByEmail(String email);
	public Boolean existsByPhoneNumber(String phoneNumber);
	public Boolean existsByEmailAndCompanyIdNot(String email,Integer companyId);
	public Boolean existsByPhoneNumberAndCompanyIdNot(String phoneNumber,Integer companyId);
}
