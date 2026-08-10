package com.naukri.driver.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "resume_info", indexes = { @Index(name = "idx_resume_id", columnList = "resume_id") })
public class Resume {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resume_id")
	@SequenceGenerator(name = "resume_id",sequenceName = "seq_resume_id", initialValue = 1, allocationSize = 1)
	@Column(name = "resume_id")
	private Integer resumeId;
	@Column(name = "description", nullable = true)
	private String summary;
	@Column(name = "academic", nullable = false)
	private String education;
	@Singular("project")
	@Column(name = "project_info", nullable = false)
	private Map<String,String> projects;
	@Column(name = "exp", nullable = false, columnDefinition = "Integer DEFAULT 0")
	private Double experience;
	@OneToOne
	private JobSeeker jobSeeker;
	
}
