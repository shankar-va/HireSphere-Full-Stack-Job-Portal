package com.naukri.model.entity;

import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.naukri.model.composite.Composite_company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@IdClass(Composite_company.class)
@Table(name = "Organisations", indexes = {

		@Index(name = "index_com_id", columnList = ("company_Id")),
		@Index(name = "index_ph_no", columnList = "ph_no") })
public class Company {
	@Id
	@Column(name = "company_Id",unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "")
	@SequenceGenerator(name = "company_id", sequenceName = "seq_com_id", allocationSize = 1, initialValue = 1)
	private Integer companyId;
	@Column(name = "company_name", nullable = false)
	private String companyName;
	@Id
	@Column(name = "email_id", nullable = false)
	private String email;
	@Id
	@Column(name = "ph_no", nullable = false)
	private String phno;
	@Column(name = "verified", nullable = false, columnDefinition = "boolean DEFAULT false")
	private Boolean isVerified;
	@Column(name = "created_At", insertable = true, updatable = false)
	@CreationTimestamp
	private LocalDate creation_date;
	@Column(name = "last_modified", insertable = false, updatable = true)
	@UpdateTimestamp
	private LocalDate last_updated;
	@OneToMany(mappedBy = "company",orphanRemoval = true)
	private Set<Recruiter> recruiters;
	@OneToMany(mappedBy = "company",orphanRemoval = true)
	private Set<Job> jobs;
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public Boolean getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}
	public LocalDate getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(LocalDate creation_date) {
		this.creation_date = creation_date;
	}
	public LocalDate getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(LocalDate last_updated) {
		this.last_updated = last_updated;
	}
	public Set<Recruiter> getRecruiters() {
		return recruiters;
	}
	public void setRecruiters(Set<Recruiter> recruiters) {
		this.recruiters = recruiters;
	}
	public Set<Job> getJobs() {
		return jobs;
	}
	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}
	
}
