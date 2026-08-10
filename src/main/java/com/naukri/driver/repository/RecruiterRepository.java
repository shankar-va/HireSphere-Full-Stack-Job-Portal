package com.naukri.driver.repository;

import com.naukri.driver.model.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterRepository extends JpaRepository<Recruiter,Integer> {
    public Boolean existsByUserUserId(Integer userId);
    public Boolean existsByEmployeeCode(String employeeCode);
    public Boolean existsByEmployeeCodeAndRecruiterIdNot(String emolyeeCode,Integer recruiterId);
}
