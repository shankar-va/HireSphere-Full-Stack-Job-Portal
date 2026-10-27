package com.hiresphere.driver.repository;


import com.hiresphere.driver.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository

public interface UserRepository extends JpaRepository<User, Long> {


    Boolean existsByEmail(String email);


    Boolean existsByPhoneNumber(String phoneNumber);


    Boolean existsByEmailAndUserIdNot(String email, Long id);


    Boolean existsByPhoneNumberAndUserIdNot(String phoneNumber, Long id);


    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.privileges WHERE u.email=:email")
    Optional<User> findUserWithRolesAndPrivileges(@Param("email") String email);


    Optional<User> findUserByEmail(String email);

}

