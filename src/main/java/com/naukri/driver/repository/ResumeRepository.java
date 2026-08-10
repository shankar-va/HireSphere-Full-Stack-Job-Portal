package com.naukri.driver.repository;

import com.naukri.driver.model.entity.Resume;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume,Integer>{
}
