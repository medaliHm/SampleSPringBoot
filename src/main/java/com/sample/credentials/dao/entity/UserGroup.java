package com.sample.credentials.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Entity
@Table(name = "MIO_SOCL_USER_GROUPE")
public class UserGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserGroupId id = new UserGroupId();

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idUser")
	private UserInfo user;

	@Column(name = "admin")
	private int admin;

	@Column(name = "DT_FIN_VAL")
	private Date dateFin;

	@Column(name = "DT_INTEGRATION")
	private Date dateInteg;

	@Column(name = "USER_INTEGRATION")
	private String userInteg;

	@Column(name = "REF_FICHIER")
	private String refFichier;

	public UserGroupId getId() {
		return id;
	}

	public void setId(UserGroupId id) {
		this.id = id;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Date getDateInteg() {
		return dateInteg;
	}

	public void setDateInteg(Date dateInteg) {
		this.dateInteg = dateInteg;
	}

	public String getUserInteg() {
		return userInteg;
	}

	public void setUserInteg(String userInteg) {
		this.userInteg = userInteg;
	}

	public String getRefFichier() {
		return refFichier;
	}

	public void setRefFichier(String refFichier) {
		this.refFichier = refFichier;
	}

}
