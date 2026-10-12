package com.naukri.driver.security;

import com.naukri.driver.model.entity.User;
import com.naukri.driver.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserWithRoles(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Username or Password!..."));

        return new CustomUserDetails(user);
    }
}
