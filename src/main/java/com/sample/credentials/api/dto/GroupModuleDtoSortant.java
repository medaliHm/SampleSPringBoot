package com.sample.credentials.api.dto;

public class GroupModuleDtoSortant {

	private String libelleGroupe;

	private long codeModule;

	private String libelleModule;

	private int lecture;

	private int ecriture;

	public long getCodeModule() {
		return codeModule;
	}

	public void setCodeModule(long codeModule) {
		this.codeModule = codeModule;
	}

	public String getLibelleGroupe() {
		return libelleGroupe;
	}

	public void setLibelleGroupe(String libelleGroupe) {
		this.libelleGroupe = libelleGroupe;
	}

	public String getLibelleModule() {
		return libelleModule;
	}

	public void setLibelleModule(String libelleModule) {
		this.libelleModule = libelleModule;
	}

	public int getLecture() {
		return lecture;
	}

	public void setLecture(int lecture) {
		this.lecture = lecture;
	}

	public int getEcriture() {
		return ecriture;
	}

	public void setEcriture(int ecriture) {
		this.ecriture = ecriture;
	}

}
