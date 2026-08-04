package com.naukri.model.composite;

import java.io.Serializable;

public class Composite_recruiter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer recruiterId;
	private String employeeCode;

	public Integer getId() {
		return recruiterId;
	}

	public void setId(Integer recruiterid) {
		this.recruiterId = recruiterid;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeCode == null) ? 0 : employeeCode.hashCode());
		result = prime * result + ((recruiterId == null) ? 0 : recruiterId.hashCode());
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
		Composite_recruiter other = (Composite_recruiter) obj;
		if (employeeCode == null) {
			if (other.employeeCode != null)
				return false;
		} else if (!employeeCode.equals(other.employeeCode))
			return false;
		if (recruiterId == null) {
			if (other.recruiterId != null)
				return false;
		} else if (!recruiterId.equals(other.recruiterId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Composite_recruiter [id=" + recruiterId + ", employeeCode=" + employeeCode + "]";
	}
}
