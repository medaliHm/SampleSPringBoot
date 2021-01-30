package com.sample.credentials.business.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.credentials.business.rules.VerifierExistenceCdModule;
import com.sample.credentials.common.config.annotation.ControleMetier;
import com.sample.credentials.common.config.service.AbstractService;
import com.sample.credentials.dao.entity.GroupeModule;
import com.sample.credentials.dao.repository.GroupModuleRepository;

@Service
@ControleMetier(VerifierExistenceCdModule.class)
public class GroupModuleInsertionService extends AbstractService<List<GroupeModule>, List<GroupeModule>> {

	@Autowired
	GroupModuleRepository groupModuleRepo;

	@Transactional
	@Override
	public List<GroupeModule> doExecute(List<GroupeModule> groups) {

		List<GroupeModule> groupeModuleAfermer = groupModuleRepo.recupOuvertGroupeModule();
		groupeModuleAfermer.forEach(gmaf -> gmaf.setDateFin(new Date()));
		groupModuleRepo.saveAll(groupeModuleAfermer);

		List<GroupeModule> groupeModulesCreated = groupModuleRepo.saveAll(groups);

		return groupeModulesCreated;

	}
}
