package com.naukri.driver.repository;

import com.naukri.driver.model.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public Boolean existsByEmail(String email);
    public Boolean existsByPhoneNumber(String phoneNumber);
    public Boolean existsByEmailAndUserIdNot(String email,Integer id);
    public Boolean existsByPhoneNumberAndUserIdNot(String phoneNumber,Integer id);
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=:email")
    public Optional<User> findUserWithRoles(@Param("email") String email);

}
