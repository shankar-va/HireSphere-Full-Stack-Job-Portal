package com.naukri.driver.model.entity;

import java.time.LocalDate;
import java.util.Set;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Organisations", indexes = {
		@Index(name = "index_com_id", columnList = "company_id"),
		@Index(name = "index_ph_no", columnList = "ph_no")
})
public class Company {

	@Id
	@Column(name = "company_id", unique = true,updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id")
	@SequenceGenerator(name = "company_id", sequenceName = "seq_com_id", allocationSize = 1)
	private Integer companyId;

	@Column(name = "company_name", nullable = false)
	private String companyName;

	@Column(name = "email_id", nullable = false, unique = true)
	private String email;

	@Column(name = "ph_no", nullable = false, unique = true)
	private String phoneNumber;

	@ColumnDefault("false")
	@Column(name = "verified", nullable = false)
	@Builder.Default
	private Boolean isVerified = false;

	@Column(name = "created_At", updatable = false)
	@CreationTimestamp
	private LocalDate creation_date;

	@Column(name = "last_modified", insertable = false)
	@UpdateTimestamp
	private LocalDate last_updated;

	@Singular
	@OneToMany(mappedBy = "company", orphanRemoval = true)
	private Set<Recruiter> recruiters;

	@Singular("job")
	@OneToMany(mappedBy = "company", orphanRemoval = true)
	private Set<Job> jobs;
}
