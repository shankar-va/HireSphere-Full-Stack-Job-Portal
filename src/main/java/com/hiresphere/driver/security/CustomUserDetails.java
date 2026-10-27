package com.hiresphere.driver.security;


import com.hiresphere.driver.model.entity.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;

import java.util.List;


@RequiredArgsConstructor

public class CustomUserDetails implements UserDetails {


    private final User user;


    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {


        List<SimpleGrantedAuthority> authorities = new ArrayList();

        if (user.getRoles() != null) {
            user.getRoles()
                .forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getRoles()
                                                                   .name()));
                    if (role.getPrivileges() != null) {
                        role.getPrivileges()
                            .forEach(privilege -> authorities.add(new SimpleGrantedAuthority(privilege.getPrivilege()
                                                                                                      .name())));
                    }
                });
        }



        user.getRoles()

            .stream()

            .flatMap(role -> role.getPrivileges()

                                 .stream())

            .map(privilege -> new SimpleGrantedAuthority(privilege.getPrivilege()

                                                                  .toString()))

            .forEach(privilege -> authorities.add(privilege));

        return authorities;

    }


    @Override

    public String getPassword() {


        return user.getPassword();

    }


    @Override

    public String getUsername() {


        return user.getEmail();

    }


    @Override

    public boolean isAccountNonExpired() {


        return Boolean.TRUE.equals(user.getIsAccountNonExpired());

    }


    @Override

    public boolean isAccountNonLocked() {


        return Boolean.TRUE.equals(user.getIsAccountNonLocked());

    }


    @Override

    public boolean isCredentialsNonExpired() {


        return Boolean.TRUE.equals(user.getIsCredentialsNonLocked());

    }


    @Override

    public boolean isEnabled() {


        return Boolean.TRUE.equals(user.getIsEnabled());
    }


}

