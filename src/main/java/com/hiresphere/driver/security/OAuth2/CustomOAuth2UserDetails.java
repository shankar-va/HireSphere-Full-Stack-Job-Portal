package com.hiresphere.driver.security.OAuth2;

import com.hiresphere.driver.model.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
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

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (user.getRoles() != null) {
            user.getRoles()
                .forEach(role -> {
                    authorities.add(
                            new SimpleGrantedAuthority(role.getRoles()
                                                           .name())
                    );

                    if (role.getPrivileges() != null) {
                        role.getPrivileges()
                            .forEach(privilege ->
                                    authorities.add(
                                            new SimpleGrantedAuthority(
                                                    privilege.getPrivilege()
                                                             .name()
                                            )
                                    )
                            );
                    }
                });
        }

        return authorities;
    }


    @Override
    public String getName() {

        return user.getEmail();
    }

}
