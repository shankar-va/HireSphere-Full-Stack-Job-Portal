package com.naukri.driver.service.impl;

import com.naukri.driver.dto.request.user.UserRegistrationRequest;
import com.naukri.driver.dto.request.user.UserUpdateRequest;
import com.naukri.driver.dto.response.user.UserResponse;
import com.naukri.driver.exception.customExceptions.user.InvalidUserDetailsException;
import com.naukri.driver.exception.customExceptions.user.UserNotFoundException;
import com.naukri.driver.mapper.user.UserMapper;
import com.naukri.driver.model.entity.User;
import com.naukri.driver.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserResponse register(UserRegistrationRequest request){
        User user=userMapper.toEntity(request);
        if((userRepository.existsByEmail(user.getEmail())||userRepository.existsByPhoneNumber(user.getPhoneNumber()))) throw new InvalidUserDetailsException("Email or Password Already exists");
        User newUser=userRepository.save(user);
        return userMapper.toResponseDTO(newUser);
    }
    public UserResponse getUserById(Integer id){
        User user=userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        return userMapper.toResponseDTO(user);
    }
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream().map(userMapper::toResponseDTO).collect(Collectors.toList());
    }
    public UserResponse updateUser(UserUpdateRequest request){

        User user=userRepository.findById(request.getUserId()).orElseThrow(()->new UserNotFoundException("User Not Found"));
        if(request.getEmail()!=null)
            if(userRepository.existsByEmailAndUserIdNot(request.getEmail(),request.getUserId()))throw new InvalidUserDetailsException("Email  already exists");
        if(request.getPhoneNumber()!=null)
            if (userRepository.existsByPhoneNumberAndUserIdNot(request.getPhoneNumber(),request.getUserId()))throw new InvalidUserDetailsException(" PhoneNumber already exists");
        User user1 = userMapper.updateUser(request, user);
        User user2 = userRepository.save(user1);
        return userMapper.toResponseDTO(user2);
    }
    public void deleteUser(Integer id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new UserNotFoundException("User Not found");
        }
    }
}
