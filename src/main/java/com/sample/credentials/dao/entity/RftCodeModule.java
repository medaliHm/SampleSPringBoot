package com.sample.credentials.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MIO_RFT_Module")
public class RftCodeModule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codeModule;

	private String name;

	public Long getCodeModule() {
		return codeModule;
	}

	public void setCodeModule(Long codeModule) {
		this.codeModule = codeModule;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
