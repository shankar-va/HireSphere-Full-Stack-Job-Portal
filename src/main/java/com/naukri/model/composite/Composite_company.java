package com.naukri.model.composite;

import java.io.Serializable;

public class Composite_company implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer companyId;
	private String email;
	private String phno;
	public Integer getId() {
		return companyId;
	}
	public void setId(Integer id) {
		this.companyId = id;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((phno == null) ? 0 : phno.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Composite_company other = (Composite_company) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (phno == null) {
			if (other.phno != null)
				return false;
		} else if (!phno.equals(other.phno))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Composite_company [id=" + companyId + ", email=" + email + ", phno=" + phno + "]";
	}
}
