package com.naukri.driver.model.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "job_seeker_info", indexes = { @Index(name = "idx_seeker_id", columnList = "job_seeker_id"),
		@Index(name = "idx_exp", columnList = "exp") })
public class JobSeeker {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_seeker_id")
	@SequenceGenerator(name = "job_seeker_id", sequenceName = "seq_job_seeker", allocationSize = 1)
	@Column(name = "job_seeker_id")
	private Integer jobseekerId;
	@Column(name = "description")
	private String headLine;
	@Column(name = "exp", nullable = false, columnDefinition = "Integer DEFAULT 0")
	private Double experience;
	@Column(name = "curr_sal", nullable = false)
	private Double currentSalary;
	@Column(name = "expect_sal")
	private Double expectedSalary;
	@Column(name = "pref_loc")
	private List<String> preferredLocation;
	@Column(name = "qualification", nullable = false)
	private String highestQualification;
	@Column(name = "available", nullable = false, columnDefinition = "Boolean DEFAULT true")
	private Boolean availableForHire;
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "jobSeeker")
	private Resume resume;
	@Singular("jobApplication")
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "jobSeeker")
	private Set<JobApplication> jobApplications;
	
}
