package com.sample.credentials.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Composite id entre la table groupe et user.
 */
@Embeddable
public class UserGroupId implements Serializable {

	@Column(name = "USER_ID_USER")
	private long idUser;

	@Column(name = "GROUPE_LIB_GROUPE")
	private String libelleGroupe;

	@Column(name = "DT_DEBUT_VAL")
	private Date dateDebut = new Date();

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getLibelleGroupe() {
		return libelleGroupe;
	}

	public void setLibelleGroupe(String libelleGroupe) {
		this.libelleGroupe = libelleGroupe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		result = prime * result + ((libelleGroupe == null) ? 0 : libelleGroupe.hashCode());
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
		UserGroupId other = (UserGroupId) obj;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		}
		if (idUser != other.idUser)
			return false;
		if (libelleGroupe == null) {
			if (other.libelleGroupe != null)
				return false;
		} else if (!libelleGroupe.equals(other.libelleGroupe))
			return false;
		return true;
	}

}
