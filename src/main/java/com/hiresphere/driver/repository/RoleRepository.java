package com.hiresphere.driver.repository;

import com.hiresphere.driver.enumaration.role.Roles;
import com.hiresphere.driver.model.entity.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @EntityGraph(attributePaths = "privileges")
    Optional<Role> findByRoles(Roles roles);

}
