package com.naukri.model.entity;

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

@Entity
@Table(name = "job_seeker_info", indexes = { @Index(name = "idx_seeker_id", columnList = "job_seeker_id"),
		@Index(name = "idx_exp", columnList = "exp") })
public class JobSeeker {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "")
	@SequenceGenerator(name = "job_seeker_id", sequenceName = "seq_job_seeker", initialValue = 1, allocationSize = 1)
	@Column(name = "job_seeker_id")
	private Integer jobseekerId;
	@Column(name = "description", nullable = true)
	private String headLine;
	@Column(name = "exp", nullable = false, columnDefinition = "Integer DEFAULT 0")
	private Integer experience;
	@Column(name = "curr_sal", nullable = false)
	private Double currentSalary;
	@Column(name = "expect_sal", nullable = true)
	private Double expectedSalary;
	@Column(name = "pref_loc", nullable = true)
	private String preferredLocation;
	@Column(name = "qualification", nullable = false)
	private String highestQualification;
	@Column(name = "available", nullable = false, columnDefinition = "Boolean DEFAULT true")
	private Boolean availableForHire;
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "jobSeeker")
	private Resume resume;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<JobApplication> jobApplication;
	
	public Integer getJobseekerId() {
		return jobseekerId;
	}
	public void setJobseekerId(Integer jobseekerId) {
		this.jobseekerId = jobseekerId;
	}
	public String getHeadLine() {
		return headLine;
	}
	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public Double getCurrentSalary() {
		return currentSalary;
	}
	public void setCurrentSalary(Double currentSalary) {
		this.currentSalary = currentSalary;
	}
	public Double getExpectedSalary() {
		return expectedSalary;
	}
	public void setExpectedSalary(Double expectedSalary) {
		this.expectedSalary = expectedSalary;
	}
	public String getPreferredLocation() {
		return preferredLocation;
	}
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}
	public String getHighestQualification() {
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
	public Boolean getAvailableForHire() {
		return availableForHire;
	}
	public void setAvailableForHire(Boolean availableForHire) {
		this.availableForHire = availableForHire;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Resume getResume() {
		return resume;
	}
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	public Set<JobApplication> getJobApplication() {
		return jobApplication;
	}
	public void setJobApplication(Set<JobApplication> jobApplication) {
		this.jobApplication = jobApplication;
	}
}
