package com.sample.credentials.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.credentials.dao.entity.GroupeModule;
import com.sample.credentials.dao.repository.GroupModuleRepository;

@Service
public class GroupeModuleRecupertationService {

	@Autowired
	private GroupModuleRepository repo;

	public List<GroupeModule> recupererGroupesEtModules() {

		return repo.recupOuvertGroupeModule();
	}
}
