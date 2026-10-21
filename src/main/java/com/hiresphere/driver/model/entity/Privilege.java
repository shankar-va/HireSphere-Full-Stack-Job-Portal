package com.hiresphere.driver.model.entity;


import com.hiresphere.driver.enumaration.role.Privileges;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.Setter;


@Entity

@Table(name = "privileges_info")

@Getter

@Setter

@AllArgsConstructor

@NoArgsConstructor

public class Privilege {


    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_id")

    @SequenceGenerator(name = "privilege_id", sequenceName = "seq_privilege_id", allocationSize = 1)

    @Column(name = "privilege_id")

    private Long privilege_id;


    @Column(name = "privilege")

    private Privileges privilege;


}

