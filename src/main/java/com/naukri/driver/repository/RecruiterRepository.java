package com.naukri.driver.repository;

import com.naukri.driver.model.entity.Recruiter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter,Integer> {
    public Boolean existsByUserUserId(Integer userId);
}
