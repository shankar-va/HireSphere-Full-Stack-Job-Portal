package com.hiresphere.driver.repository;

import com.hiresphere.driver.enumaration.role.Privileges;
import com.hiresphere.driver.model.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Optional<Privilege> findByPrivilege(Privileges privilege);

}
