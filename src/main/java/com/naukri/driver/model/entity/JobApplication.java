package com.naukri.driver.model.entity;

import java.time.LocalDate;

import com.naukri.driver.enumaration.jobApplication.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "job_application_info", indexes = { @Index(name = "idx_job_app_id", columnList = "job_app_id") },uniqueConstraints = @UniqueConstraint(name = "uk_job_jobSeeker",columnNames = {"job_id,job_seeker_id"}))
public class JobApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_app_id")
	@SequenceGenerator(name = "job_app_id", sequenceName = "seq_job_app_id", allocationSize = 1)
	@Column(name = "job_app_id")
	private Integer applicationId;
	@CreationTimestamp
	@Column(name = "applied_date", nullable = false, updatable = false)
	private LocalDate appliedDate;
	@Column(name = "status", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private ApplicationStatus status;
	@Column(name = "cv", nullable = false)
	private String coverletter;
	@Column(name = "join_date")
	private LocalDate expectedJoinDate;
	@Column(name = "interview_date")
	private LocalDate interviewDate;
	@Column(name = "offered_sal")
	private Double offerSalary;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_seeker_id",nullable = false)
	private JobSeeker jobSeeker;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id",nullable = false)
	private Job job;
	
}
