package com.hiresphere.driver.security;


import com.hiresphere.driver.model.entity.User;

import com.hiresphere.driver.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;


@Component

public class CustomUserDetailsService implements UserDetailsService {


    UserRepository userRepository;



    @Override

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        User user = userRepository.findUserWithRoles(email)

                                  .orElseThrow(() -> new UsernameNotFoundException("Invalid Username or Password!..."));


        return new CustomUserDetails(user);

    }


}

