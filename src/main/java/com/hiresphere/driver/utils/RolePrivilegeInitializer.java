package com.hiresphere.driver.utils;

import com.hiresphere.driver.enumaration.role.Privileges;
import com.hiresphere.driver.enumaration.role.Roles;
import com.hiresphere.driver.model.entity.Privilege;
import com.hiresphere.driver.model.entity.Role;
import com.hiresphere.driver.repository.PrivilegeRepository;
import com.hiresphere.driver.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RolePrivilegeInitializer {

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;


    @Transactional
    public void initialize() {

        for (Roles role : Roles.values()) {
            roleRepository.findByRoles(role)
                          .orElseGet(() -> roleRepository.save(Role.builder()
                                                                   .roles(role)
                                                                   .build()));
        }
        for (Privileges privilege : Privileges.values()) {
            privilegeRepository.findByPrivilege(privilege)
                               .orElseGet(() -> privilegeRepository.save(Privilege.builder()
                                                                                  .privilege(privilege)
                                                                                  .build()));
        }
    }

}
