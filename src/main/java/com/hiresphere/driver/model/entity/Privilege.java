package com.hiresphere.driver.model.entity;


import com.hiresphere.driver.enumaration.role.Privileges;

import jakarta.persistence.*;

import lombok.*;


@Entity

@Table(name = "privileges_info")

@Getter

@Setter

@AllArgsConstructor

@NoArgsConstructor

@Builder

public class Privilege {


    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_id")

    @SequenceGenerator(name = "privilege_id", sequenceName = "seq_privilege_id", allocationSize = 1)

    @Column(name = "privilege_id")

    private Long privilege_id;


    @Column(name = "privilege", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)

    @Builder.Default

    private Privileges privilege = Privileges.PROFILE_READ;


}

