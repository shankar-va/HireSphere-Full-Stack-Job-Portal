package com.naukri.driver.model.entity;

import java.time.LocalDate;
import java.util.Set;

import com.naukri.driver.enumaration.role.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_info", indexes = { @Index(name = "index_by_id", columnList = ("user_id")),
		@Index(name = "index_by_phno", columnList = ("ph_no")) })
public class User {
	@Id
	@Column(name = "user_id", unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
	@SequenceGenerator(name = "user_id", sequenceName = "seq_user_id", initialValue = 1, allocationSize = 1)
	private Integer userId;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = true)
	private String lastName;
	@Column(name = "email_id", nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "ph_no", precision = 10, scale = 0, unique = true)
	private String phoneNumber;
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
	@Builder.Default
	@Column(name = "is_account_non_locked")
	private Boolean isAccountNonLocked=true;
	@Builder.Default
	@Column(name = "is_account_non_expired")
	private Boolean isAccountNonExpired=true;
	@Builder.Default
	@Column(name = "is_credentials_non_locked")
	private Boolean isCredentialsNonLocked=true;
	@Builder.Default
	@Column(name = "is_enabled")
	private Boolean isEnabled;
	@ManyToMany
	@Column(name = "role_id")
	private Set<Role> roles;
}
