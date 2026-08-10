package com.naukri.driver.dto.request.user;

import com.naukri.driver.validation.interfaces.ValidatePhno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    @NotNull
    Integer userId;
    @Pattern(regexp = ".*\\S.*",message = "firstName cannot be blank if provided")
    String firstName;
    @Pattern(regexp = ".*\\S.*", message = "lastName cannot be blank if provided")
    String lastName;
    @Pattern(regexp = ".*\\S.*",message = "Email cannot be blank if provided")
    @Email(message = "Invalid Email Id")
    String email;
    @Size(min = 10,message = "Provide proper Phone Number")
    @ValidatePhno(message = "Invalid phoneNumber")
    String phoneNumber;
}
