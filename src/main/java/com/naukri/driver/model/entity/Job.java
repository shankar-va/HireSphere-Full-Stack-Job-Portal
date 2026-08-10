package com.naukri.driver.model.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "job_info", indexes = { @Index(name = "idx_job_id", columnList = "job_id") })
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_id")
	@SequenceGenerator(name = "job_id", sequenceName = "seq_job_id", initialValue = 1, allocationSize = 1)
	@Column(name = "job_id")
	private Integer jobId;
	@Column(name = "job-title", nullable = false)
	private String title;
	@Column(name = "job_description", nullable = false)
	private String description;
	@Column(name = "emp_mode", nullable = true)
	private List<String> employmentMode;
	@Column(name = "exp", nullable = true)
	private Double experienceRequired;
	@Column(name = "min_sal", nullable = true)
	private Double minimum_sal;
	@Column(name = "max_sal", nullable = true)
	private Double maximum_sal;
	@Column(name = "loc", nullable = false)
	private List<String> preferredLocations;
	@Column(name = "vacancy", nullable = false, columnDefinition = "Integer DEFAULT 0")
	private Integer vacancies;
	@Column(name = "posted_on", nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	private LocalDate postedDate;
	@Column(name = "deadline", nullable = false)
	private LocalDate applicationDeadLine;
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;
	@ManyToOne(fetch = FetchType.LAZY)
	private Recruiter recruiter;
	@Singular("jobApplication")
	@OneToMany(mappedBy = "job",orphanRemoval = true)
	private Set<JobApplication> jobApplications;
	
}
