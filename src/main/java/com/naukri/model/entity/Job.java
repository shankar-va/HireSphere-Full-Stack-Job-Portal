package com.naukri.model.entity;

import java.time.LocalDate;
import java.util.Set;

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

@Entity
@Table(name = "job_info", indexes = { @Index(name = "idx_job_id", columnList = "job_id") })
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_id")
	@SequenceGenerator(name = "job_id", sequenceName = "seq_job_id", initialValue = 1, allocationSize = 1)
	@Column(name = "job_id")
	private Integer id;
	@Column(name = "job-title", nullable = false)
	private String title;
	@Column(name = "job_description", nullable = false)
	private String description;
	@Column(name = "emp_mode", nullable = true)
	private String employmentMode;
	@Column(name = "exp", nullable = true)
	private String experienceRequired;
	@Column(name = "min_sal", nullable = true)
	private Double minimum_sal;
	@Column(name = "max_sal", nullable = true)
	private Double maximum_sal;
	@Column(name = "loc", nullable = false)
	private String location;
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
	@OneToMany(mappedBy = "job",orphanRemoval = true)
	private Set<JobApplication> jopApplications;
	public Integer getUserId() {
		return id;
	}
	public void setUserId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmploymentMode() {
		return employmentMode;
	}
	public void setEmploymentMode(String employmentMode) {
		this.employmentMode = employmentMode;
	}
	public String getExperienceRequired() {
		return experienceRequired;
	}
	public void setExperienceRequired(String experienceRequired) {
		this.experienceRequired = experienceRequired;
	}
	public Double getMinimum_sal() {
		return minimum_sal;
	}
	public void setMinimum_sal(Double minimum_sal) {
		this.minimum_sal = minimum_sal;
	}
	public Double getMaximum_sal() {
		return maximum_sal;
	}
	public void setMaximum_sal(Double maximum_sal) {
		this.maximum_sal = maximum_sal;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getVacancies() {
		return vacancies;
	}
	public void setVacancies(Integer vacancies) {
		this.vacancies = vacancies;
	}
	public LocalDate getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}
	public LocalDate getApplicationDeadLine() {
		return applicationDeadLine;
	}
	public void setApplicationDeadLine(LocalDate applicationDeadLine) {
		this.applicationDeadLine = applicationDeadLine;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Recruiter getRecruiter() {
		return recruiter;
	}
	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}
	public Set<JobApplication> getJopApplications() {
		return jopApplications;
	}
	public void setJopApplications(Set<JobApplication> jopApplications) {
		this.jopApplications = jopApplications;
	}
}
