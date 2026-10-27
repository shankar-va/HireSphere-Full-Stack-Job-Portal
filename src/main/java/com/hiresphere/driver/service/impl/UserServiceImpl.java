package com.hiresphere.driver.service.impl;


import com.hiresphere.driver.dto.request.user.UserRegistrationRequest;
import com.hiresphere.driver.dto.request.user.UserUpdateRequest;
import com.hiresphere.driver.dto.response.user.UserResponse;
import com.hiresphere.driver.enumaration.role.Roles;
import com.hiresphere.driver.exception.customExceptions.user.InvalidUserDetailsException;
import com.hiresphere.driver.exception.customExceptions.user.UserNotFoundException;
import com.hiresphere.driver.mapper.user.UserMapper;
import com.hiresphere.driver.model.entity.Role;
import com.hiresphere.driver.model.entity.User;
import com.hiresphere.driver.repository.RoleRepository;
import com.hiresphere.driver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service

@RequiredArgsConstructor

public class UserServiceImpl {


    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;


    public UserResponse register(UserRegistrationRequest request) {


        User user = userMapper.toEntity(request);

        if ((userRepository.existsByEmail(user.getEmail()) || userRepository.existsByPhoneNumber(user.getPhoneNumber())))

            throw new InvalidUserDetailsException("Email or Password Already exists");
        Role defaultRole = roleRepository.findByRoles(Roles.ROLE_USER)
                                         .orElseThrow(() ->
                                                 new IllegalStateException("ROLE_USER is missing from the database")
                                         );
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User newUser = userRepository.save(user);

        return userMapper.toResponseDTO(newUser);

    }


    public UserResponse getUserById(Integer id) {


        User user = userRepository.findById(Long.valueOf(id))

                                  .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        return userMapper.toResponseDTO(user);

    }


    public List<UserResponse> getUsers() {


        return userRepository.findAll()

                             .stream()

                             .map(userMapper::toResponseDTO)

                             .collect(Collectors.toList());

    }


    public UserResponse updateUser(UserUpdateRequest request) {


        User user = userRepository.findById(Long.valueOf(request.getUserId()))

                                  .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        if (request.getEmail() != null)

            if (userRepository.existsByEmailAndUserIdNot(request.getEmail(), Long.valueOf(request.getUserId())))

                throw new InvalidUserDetailsException("Email  already exists");

        if (request.getPhoneNumber() != null)

            if (userRepository.existsByPhoneNumberAndUserIdNot(request.getPhoneNumber(), Long.valueOf(request.getUserId())))

                throw new InvalidUserDetailsException(" PhoneNumber already exists");

        User user1 = userMapper.updateUser(request, user);

        User user2 = userRepository.save(user1);

        return userMapper.toResponseDTO(user2);

    }


    public void deleteUser(Integer id) {


        if (userRepository.existsById(Long.valueOf(id))) {

            userRepository.deleteById(Long.valueOf(id));

        } else {

            throw new UserNotFoundException("User Not found");

        }

    }


    public UserResponse getUserByEmail(String email) {


        User user = userRepository.findUserByEmail(email)
                                  .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
        return userMapper.toResponseDTO(user);
    }

}

