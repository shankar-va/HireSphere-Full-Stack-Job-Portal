package com.hiresphere.driver.repository;


import com.hiresphere.driver.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository

public interface UserRepository extends JpaRepository<User, Integer> {


    Boolean existsByEmail(String email);



    Boolean existsByPhoneNumber(String phoneNumber);



    Boolean existsByEmailAndUserIdNot(String email, Integer id);



    Boolean existsByPhoneNumberAndUserIdNot(String phoneNumber, Integer id);



    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=:email")

    Optional<User> findUserWithRoles(@Param("email") String email);


}

