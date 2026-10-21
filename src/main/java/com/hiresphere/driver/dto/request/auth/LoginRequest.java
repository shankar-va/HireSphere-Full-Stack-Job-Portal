package com.hiresphere.driver.dto.request.auth;


import com.hiresphere.driver.validation.interfaces.ValidatePassword;

import jakarta.validation.constraints.Email;


public record LoginRequest(


        @Email String email,

        @ValidatePassword String password) {


}

