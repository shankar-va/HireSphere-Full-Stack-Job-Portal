package com.hiresphere.driver.dto.response.user;


import lombok.Builder;

import lombok.Value;


import java.time.LocalDate;


@Value

@Builder

public class UserResponse {


    Integer userId;


    String firstName;


    String lastName;


    String email;


    String phoneNumber;


    LocalDate createdAt;


}

