package com.naukri.model.composite;

import java.io.Serializable;

public class Composite_user implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int userId;
	private String phno;
	private String email;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((phno == null) ? 0 : phno.hashCode());
		result = prime * result + userId;
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
		Composite_user other = (Composite_user) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (phno == null) {
			if (other.phno != null)
				return false;
		} else if (!phno.equals(other.phno))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Composite_user [userId=" + userId + ", phno=" + phno + ", email=" + email + "]";
	}
}
