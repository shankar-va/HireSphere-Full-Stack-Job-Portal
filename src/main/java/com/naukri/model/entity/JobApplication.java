package com.naukri.model.entity;

import java.time.LocalDate;

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

@Entity
@Table(name = "job_application_info", indexes = { @Index(name = "idx_job_app_id", columnList = "job_app_id") })
public class JobApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_app_id")
	@SequenceGenerator(name = "job_app_id", sequenceName = "seq_job_app_id", initialValue = 1, allocationSize = 1)
	private Integer id;
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
	public Integer getUserId() {
		return id;
	}
	public void setUserId(Integer id) {
		this.id = id;
	}
	public LocalDate getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCoverletter() {
		return coverletter;
	}
	public void setCoverletter(String coverletter) {
		this.coverletter = coverletter;
	}
	public LocalDate getExpected_Join_Date() {
		return expected_Join_Date;
	}
	public void setExpected_Join_Date(LocalDate expected_Join_Date) {
		this.expected_Join_Date = expected_Join_Date;
	}
	public LocalDate getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(LocalDate interviewDate) {
		this.interviewDate = interviewDate;
	}
	public Double getOfferSalary() {
		return offerSalary;
	}
	public void setOfferSalary(Double offerSalary) {
		this.offerSalary = offerSalary;
	}
	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}
	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	
}
