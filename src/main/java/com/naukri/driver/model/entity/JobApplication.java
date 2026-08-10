package com.naukri.driver.model.entity;

import java.time.LocalDate;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "job_application_info", indexes = { @Index(name = "idx_job_app_id", columnList = "job_app_id") })
public class JobApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_app_id")
	@SequenceGenerator(name = "job_app_id", sequenceName = "seq_job_app_id", initialValue = 1, allocationSize = 1)
	private Integer applicationId;
	@CreationTimestamp
	@Column(name = "applied_date", nullable = false, insertable = true, updatable = false)
	private LocalDate appliedDate;
	@Column(name = "status", nullable = false)
	private String status;
	@Column(name = "cv", nullable = false)
	private String coverletter;
	@Column(name = "join_date", nullable = true)
	private LocalDate expected_Join_Date;
	@Column(name = "interview_date")
	private LocalDate interviewDate;
	@Column(name = "offered_sal", nullable = true)
	private Double offerSalary;
	@ManyToOne(fetch = FetchType.LAZY)
	private JobSeeker jobSeeker;
	@ManyToOne(fetch = FetchType.LAZY)
	private Job job;
	
}
