package com.hiresphere.driver.security.OAuth2;

import com.hiresphere.driver.model.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class CustomOAuth2UserDetails implements OAuth2User, UserDetails {

    private final User user;

    private final Map<String, Object> attributes;


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

        return user.getIsAccountNonExpired();
    }


    @Override
    public boolean isAccountNonLocked() {

        return user.getIsAccountNonLocked();
    }


    @Override
    public boolean isCredentialsNonExpired() {

        return user.getIsCredentialsNonLocked();
    }


    @Override
    public boolean isEnabled() {

        return user.getIsEnabled();
    }


    @Override
    public Map<String, Object> getAttributes() {

        return attributes;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = user.getRoles()
                                                       .stream()
                                                       .map(role -> new SimpleGrantedAuthority(role.getRoles()
                                                                                                   .toString()))
                                                       .toList();
        user.getRoles()
            .stream()
            .flatMap(role -> role.getPrivileges()
                                 .stream())
            .map(privilege -> new SimpleGrantedAuthority(privilege.getPrivilege()
                                                                  .toString()))
            .forEach(authorities::add);
        return authorities;
    }


    @Override
    public String getName() {

        return user.getEmail();
    }

}
