package com.naukri.driver.repository;

import com.naukri.driver.model.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public Boolean existsByEmail(String email);
    public Boolean existsByPhoneNumber(String phoneNumber);
    public Boolean existsByEmailAndUserIdNot(String email,Integer id);
    public Boolean existsByPhoneNumberAndUserIdNot(String phoneNumber,Integer id);

}
