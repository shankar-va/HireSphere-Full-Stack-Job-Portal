package com.naukri.driver.repository;

import com.naukri.driver.model.entity.Company;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer>{

    public Company getCompanyByCompanyName(String companyName);
}
