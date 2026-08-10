package com.naukri.driver.model.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
	@Column(name = "job_title", nullable = false)
	private String title;
	@Column(name = "job_description", nullable = false)
	private String description;
	@ElementCollection
	private Set<String> employmentMode;
	@Column(name = "min_exp")
	private Double minimumExperienceRequired;
	@Column(name = "max_exp")
	private Double maximumExperienceRequired;
	@Column(name = "min_sal")
	private Double minimum_sal;
	@Column(name = "max_sal")
	private Double maximum_sal;
	@ElementCollection
	private Set<String> preferredLocations;
	@Column(name = "vacancy", nullable = false, columnDefinition = "Integer DEFAULT 0")
	private Integer vacancies;
	@Column(name = "posted_on", nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	private LocalDate postedDate;
	@Column(name = "deadline", nullable = false)
	private LocalDate applicationDeadLine;
	@Column(name = "is_closed",nullable = false,columnDefinition = "Boolean DEFAULT false")
	private Boolean isClosed;
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;
	@ManyToOne(fetch = FetchType.LAZY)
	private Recruiter recruiter;
	@Singular("jobApplication")
	@OneToMany(mappedBy = "job")
	private Set<JobApplication> jobApplications;
	
}
