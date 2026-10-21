package com.hiresphere.driver.repository;


import com.hiresphere.driver.model.entity.Resume;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository

public interface ResumeRepository extends JpaRepository<Resume, Integer> {


    boolean existsByJobSeekerJobSeekerId(Integer jobSeekerId);



    Optional<Resume> findByJobSeekerJobSeekerId(Integer jobSeekerId);


}
