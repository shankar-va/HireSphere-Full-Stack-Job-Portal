package com.naukri.driver.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
		name = "resume_info",
		indexes = {
				@Index(
						name = "idx_resume_id",
						columnList = "resume_id"
				)
		}
)
public class Resume {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "resume_id"
	)
	@SequenceGenerator(
			name = "resume_id",
			sequenceName = "seq_resume_id",
			initialValue = 1,
			allocationSize = 1
	)
	@Column(name = "resume_id")
	private Integer resumeId;

	@Column(name = "description")
	private String summary;

	@Column(name = "academic", nullable = false)
	private String education;

	@ElementCollection
	@CollectionTable(
			name = "resume_projects",
			joinColumns = @JoinColumn(name = "resume_id")
	)
	@MapKeyColumn(name = "project_name")
	@Column(name = "project_description")
	private Map<String, String> projects;

	@Column(
			name = "exp",
			nullable = false
	)
	private Double experience;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(
			name = "job_seeker_id",
			nullable = false,
			unique = true
	)
	private JobSeeker jobSeeker;
}