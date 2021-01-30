package com.sample.credentials.api.dto;

import java.util.Date;
import java.util.List;


public class ModulesGroupsDto {
	
	
	private List<GroupModuleDto> groups;
	
	private Date dateInteg;

	private String userInteg;

	private String refFichier;

	public List<GroupModuleDto> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupModuleDto> groups) {
		this.groups = groups;
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
