package com.sample.credentials.api.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.credentials.api.dto.GroupModuleDtoSortant;
import com.sample.credentials.api.dto.ModulesGroupsDto;
import com.sample.credentials.api.dto.UserGroupDto;
import com.sample.credentials.api.dto.UsersGroupDto;
import com.sample.credentials.business.service.GroupModuleInsertionService;
import com.sample.credentials.business.service.GroupeModuleRecupertationService;
import com.sample.credentials.business.service.UserGroupInsertionService;
import com.sample.credentials.business.service.UserGroupRecuperationService;
import com.sample.credentials.common.config.exception.ExceptionMetier;
import com.sample.credentials.common.mapper.GroupeMapper;
import com.sample.credentials.dao.entity.GroupeModule;
import com.sample.credentials.dao.entity.UserGroup;
import com.sample.credentials.dao.repository.GroupModuleRepository;

@Component
public class HabilitationAdaptateur {

	@Autowired
	private GroupeMapper groupeMapper;

	@Autowired
	private GroupeModuleRecupertationService recuperationService;

	@Autowired
	private GroupModuleRepository repositoryGroupe;

	@Autowired
	private UserGroupRecuperationService userRecuperationService;

	@Autowired
	private GroupModuleInsertionService insertionService;

	@Autowired
	private UserGroupInsertionService userInsertionService;

	public List<GroupModuleDtoSortant> insererGroupeEtModule(ModulesGroupsDto dto) throws ExceptionMetier {

		List<GroupeModule> groupeModules = groupeMapper.dtoToListGroupModuleDto(dto);

		return groupeMapper.listGroupeModuleToListGroupModuleDtoSortant(insertionService.execute(groupeModules));
	}

	public List<GroupModuleDtoSortant> getGroupesEtModules() {

		return groupeMapper
				.listGroupeModuleToListGroupModuleDtoSortant(recuperationService.recupererGroupesEtModules());
	}

	public List<GroupModuleDtoSortant> getGroupesEtModulesByLibGroupe(String libGroupe) {

		return groupeMapper
				.listGroupeModuleToListGroupModuleDtoSortant(repositoryGroupe.getGroupeModuleByLibGroupe(libGroupe));
	}

	public List<UserGroupDto> insererUsersEtGroupes(UsersGroupDto dto) throws ExceptionMetier {

		List<UserGroup> groupeModules = groupeMapper.dtoToListUserGroup(dto);

		return groupeMapper.listUserGroupToListUserGroupDto(userInsertionService.execute(groupeModules));
	}

	public List<UserGroupDto> getActifUsers() throws ExceptionMetier {

		return groupeMapper.listUserGroupToListUserGroupDto(userRecuperationService.recupererUsersEtGroupes());
	}
}
