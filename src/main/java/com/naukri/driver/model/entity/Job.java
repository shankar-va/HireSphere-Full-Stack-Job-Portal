package com.naukri.driver.model.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.naukri.driver.enumaration.job.EmploymentMode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
	@SequenceGenerator(name = "job_id", sequenceName = "seq_job_id", allocationSize = 1)
	@Column(name = "job_id")
	private Integer jobId;
	@Column(name = "job_title", nullable = false)
	private String title;
	@Column(name = "job_description", nullable = false)
	private String description;
	@Enumerated(EnumType.STRING)
	@ElementCollection
	@CollectionTable(
			name = "job_employment_mode",
			joinColumns = @JoinColumn(name = "job_info_job_id",nullable = false)
	)
	@Column(name = "employment_mode",nullable = false)
	private Set<EmploymentMode> employmentMode;
	@Column(name = "min_exp")
	private Double minimumExperienceRequired;
	@Column(name = "max_exp")
	private Double maximumExperienceRequired;
	@Column(name = "min_sal")
	private Double minimum_sal;
	@Column(name = "max_sal")
	private Double maximum_sal;
	@ElementCollection
	@CollectionTable(
			name = "job_preferred_locations",
			joinColumns = @JoinColumn(name = "job_info_job_id", nullable = false)
	)
	@Column(name = "location", nullable = false)
	private List<String> preferredLocations;
	@Column(name = "vacancy", nullable = false, columnDefinition = "Integer DEFAULT 0")
	private Integer vacancies;
	@Column(name = "posted_on", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDate postedDate;
	@Column(name = "deadline", nullable = false)
	private LocalDate applicationDeadLine;
	@Builder.Default
	@ColumnDefault("false")
	@Column(name = "is_closed",nullable = false)
	private Boolean isClosed=false;
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;
	@ManyToOne(fetch = FetchType.LAZY)
	private Recruiter recruiter;
	@Singular("jobApplication")
	@OneToMany(mappedBy = "job")
	private Set<JobApplication> jobApplications;
	
}
