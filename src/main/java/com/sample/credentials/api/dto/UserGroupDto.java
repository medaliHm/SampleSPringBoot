package com.sample.credentials.api.dto;

import java.io.Serializable;

public class UserGroupDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idCnp;
	private String email;
	private String libelleGroupe;
	private int admin;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCnp() {
		return idCnp;
	}

	public void setIdCnp(String idCnp) {
		this.idCnp = idCnp;
	}

	public String getLibelleGroupe() {
		return libelleGroupe;
	}

	public void setLibelleGroupe(String libelleGroupe) {
		this.libelleGroupe = libelleGroupe;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}
}
