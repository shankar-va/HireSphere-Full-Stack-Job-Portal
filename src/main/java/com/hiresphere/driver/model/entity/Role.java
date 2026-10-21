package com.hiresphere.driver.model.entity;


import com.hiresphere.driver.enumaration.role.Roles;

import jakarta.persistence.*;

import lombok.*;


import java.util.HashSet;
import java.util.Set;


@Builder

@Entity

@Table(name = "role_info")

@NoArgsConstructor

@AllArgsConstructor

@Getter

@Setter

public class Role {


    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id")

    @SequenceGenerator(name = "role_id", sequenceName = "seq_role_id", allocationSize = 1)

    @Column(name = "role_id")

    private Long roleId;


    @Column(name = "roles")

    @Enumerated(EnumType.STRING)

    @Builder.Default

    private Roles roles = Roles.ROLE_USER;


    @ManyToMany

    @JoinTable(name = "role_info_privileges",joinColumns = @JoinColumn(name = "role_id"),inverseJoinColumns = @JoinColumn(name = "privilege_id"))

    private Set<Privilege> privileges=new HashSet<>();


}

