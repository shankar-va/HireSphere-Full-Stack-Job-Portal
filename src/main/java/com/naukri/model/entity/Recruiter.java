package com.naukri.model.entity;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.naukri.model.composite.Composite_recruiter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@IdClass(Composite_recruiter.class)
@Table(name = "recruiter_info", indexes = { @Index(name = "idx_rec_id", columnList = "rec_id"),
		@Index(name = "idx_emp_code", columnList = "emp_code") })
public class Recruiter {
	@Id
	@Column(name = "rec_id",unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rec_id")
	@SequenceGenerator(name = "rec_id", sequenceName = "seq_rec_id", initialValue = 1, allocationSize = 1)
	private Integer recruiterId;
	@Column(name = "designation", nullable = false)
	private String designation;
	@Column(name = "department", nullable = false)
	private String domain;
	@Id
	@Column(name = "emp_code", nullable = false)
	private String employeeCode;
	@Column(name = "exp", nullable = false, columnDefinition = "Integer DEFAULT 0")
	private Integer experience;
	@Column(name = "created_At", nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	private LocalDateTime joinedDate;
	@Column(name = "active", nullable = false, columnDefinition = "Boolean DEFAULT false")
	private Boolean isActive;
	@OneToOne
	private User user;
	@ManyToOne
	private Company company;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "recruiter")
	private Set<Job> jobs;
	public Integer getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(Integer recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public LocalDateTime getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(LocalDateTime joinedDate) {
		this.joinedDate = joinedDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Set<Job> getJobs() {
		return jobs;
	}
	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}
	
	
}
