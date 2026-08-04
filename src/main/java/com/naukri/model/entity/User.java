package com.naukri.model.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.naukri.model.composite.Composite_user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@IdClass(Composite_user.class)
@Table(name = "user_info", indexes = { @Index(name = "index_by_id", columnList = ("user_id")),
		@Index(name = "index_by_phno", columnList = ("ph_no")) })
public class User {
	@Id
	@Column(name = "user_id", unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
	@SequenceGenerator(name = "user_id", sequenceName = "seq_user_id", initialValue = 1, allocationSize = 1)
	private Integer userId;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = true)
	private String lastName;
	@Id
	@Column(name = "email_id", nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String pswd;
	@Id
	@Column(name = "ph_no", precision = 10, scale = 0, unique = true)
	private String phno;
	@CreationTimestamp
	@Column(name = "created_date", insertable = true, updatable = false)
	private LocalDate createdAt;
	@UpdateTimestamp
	@Column(name = "last_modified", insertable = false, updatable = true)
	private LocalDate updatedAt;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	private JobSeeker jobSeeker;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	private Recruiter recruiter;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer id) {
		this.userId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

}
