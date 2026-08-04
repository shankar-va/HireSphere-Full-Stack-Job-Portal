package com.naukri.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "resume_info", indexes = { @Index(name = "idx_resume_id", columnList = "resume_id") })
public class Resume {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resume_id")
	@SequenceGenerator(name = "resume_id", initialValue = 1, allocationSize = 1)
	@Column(name = "resume_id")
	private Integer id;
	@Column(name = "descrition", nullable = true)
	private String summary;
	@Column(name = "academic", nullable = false)
	private String education;
	@Column(name = "project_info", nullable = false)
	private List<String> project;
	@Column(name = "exp", nullable = false, columnDefinition = "Integer DEFAULT 0")
	private Integer experience;
	@OneToOne
	private JobSeeker jobSeeker;
	public Integer getUserId() {
		return id;
	}
	public void setUserId(Integer id) {
		this.id = id;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public List<String> getProject() {
		return project;
	}
	public void setProject(List<String> project) {
		this.project = project;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public JobSeeker getUser() {
		return jobSeeker;
	}
	public void setUser(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
}
