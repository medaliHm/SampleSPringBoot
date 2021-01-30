package com.sample.credentials.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UsersGroupDto implements Serializable {

	private List<UserGroupDto> users;

	private Date dateInteg;

	private String userInteg;

	private String refFichier;

	public List<UserGroupDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserGroupDto> users) {
		this.users = users;
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
