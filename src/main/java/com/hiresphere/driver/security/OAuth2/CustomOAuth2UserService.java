package com.hiresphere.driver.security.OAuth2;

import com.hiresphere.driver.enumaration.role.Roles;
import com.hiresphere.driver.model.entity.Role;
import com.hiresphere.driver.model.entity.User;
import com.hiresphere.driver.repository.RoleRepository;
import com.hiresphere.driver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User user = super.loadUser(userRequest);
        String email = user.getAttribute("email");
        if (email == null || email.isBlank()) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 service provider");
        }
        User newUser = userRepository.findUserWithRolesAndPrivileges(email)
                                     .orElseGet(() -> registerNewUser(user, email));
        return new CustomOAuth2UserDetails(newUser, user.getAttributes());
    }


    private User registerNewUser(OAuth2User user, String email) {

        String firstName = user.getAttribute("given_name");
        String lastName = user.getAttribute("family_name");
        if (firstName == null) firstName = user.getAttribute("name");

        Role role = roleRepository.findByRoles(Roles.ROLE_USER)
                                  .orElseThrow(() -> new IllegalStateException("Role User is missing from the database"));
        User newUser = User.builder()
                           .email(email)
                           .firstName(firstName)
                           .lastName(lastName)
                           .username(email)
                           .password(passwordEncoder.encode(UUID.randomUUID()
                                                                .toString()))
                           .roles(Set.of(role))
                           .phoneNumber(null)
                           .isAccountNonLocked(true)
                           .isAccountNonExpired(true)
                           .isCredentialsNonLocked(true)
                           .isEnabled(true)
                           .build();
        return userRepository.save(newUser);
    }

}
