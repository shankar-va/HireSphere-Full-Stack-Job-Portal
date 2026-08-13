package com.naukri.driver.model.entity;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "recruiter_info", indexes = {
        @Index(name = "idx_emp_code", columnList = "emp_code")})
public class Recruiter {
    @Id
    @Column(name = "rec_id", unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rec_id")
    @SequenceGenerator(name = "rec_id", sequenceName = "seq_rec_id", allocationSize = 1)
    private Integer recruiterId;
    @Column(name = "designation", nullable = false)
    private String designation;
    @Column(name = "department", nullable = false)
    private String domain;
    @Column(name = "emp_code", nullable = false, unique = true)
    private String employeeCode;
    @Column(name = "exp", nullable = false)
    private Double experience=0.0;
    @Column(name = "created_At", nullable = false,  updatable = false)
    @CreationTimestamp
    private LocalDateTime joinedDate;
    @Builder.Default
    @ColumnDefault("true")
    @Column(name = "active",nullable = false)
    private Boolean isActive=true;
    @OneToOne
    private User user;
    @ManyToOne
    private Company company;
    @Singular("job")
    @OneToMany(mappedBy = "recruiter",orphanRemoval = true)
    private Set<Job> jobs;

}
