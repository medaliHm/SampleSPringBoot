package com.sample.credentials.common.mapper;

import java.util.Date;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sample.credentials.api.dto.GroupModuleDto;
import com.sample.credentials.api.dto.GroupModuleDtoSortant;
import com.sample.credentials.api.dto.ModulesGroupsDto;
import com.sample.credentials.api.dto.UserGroupDto;
import com.sample.credentials.api.dto.UsersGroupDto;
import com.sample.credentials.dao.entity.GroupeModule;
import com.sample.credentials.dao.entity.UserGroup;

@Mapper(componentModel = "spring")
public interface GroupeMapper {

	@Mapping(source = "libelleGroupe", target = "id.libelleGroup")
	@Mapping(source = "codeModule", target = "id.codeModule")
	@Mapping(source = "codeModule", target = "module.codeModule")
	@Mapping(source = "lecture", target = "canRead")
	@Mapping(source = "ecriture", target = "canWrite")
	GroupeModule groupModuleDtoToGroupeModule(GroupModuleDto gmd);

	@Mapping(source = "id.libelleGroup", target = "libelleGroupe")
	@Mapping(source = "module.name", target = "libelleModule")
	@Mapping(source = "module.codeModule", target = "codeModule")
	@Mapping(source = "canRead", target = "lecture")
	@Mapping(source = "canWrite", target = "ecriture")
	GroupModuleDtoSortant groupeModuleToGroupModuleDtoSortant(GroupeModule gmd);

	List<GroupeModule> listGroupeModuleDtoToListGroupeModule(List<GroupModuleDto> list);

	List<GroupModuleDtoSortant> listGroupeModuleToListGroupModuleDtoSortant(List<GroupeModule> list);

	@Mapping(source = "idCnp", target = "user.name")
	@Mapping(source = "libelleGroupe", target = "id.libelleGroupe")
	@Mapping(source = "admin", target = "admin")
	UserGroup userGroupDtoToUserGroup(UserGroupDto dto);

	@Mapping(target = "idCnp", source = "user.name")
	@Mapping(target = "libelleGroupe", source = "id.libelleGroupe")
	@Mapping(target = "admin", source = "admin")
	UserGroupDto userGroupToUserGroupDto(UserGroup domain);

	List<UserGroup> listUserGroupDtoToListUserGroup(List<UserGroupDto> list);

	List<UserGroupDto> listUserGroupToListUserGroupDto(List<UserGroup> list);

	default List<UserGroup> dtoToListUserGroup(UsersGroupDto dto) {

		List<UserGroup> usersgroupe = listUserGroupDtoToListUserGroup(dto.getUsers());
		usersgroupe.forEach(gm -> {
			gm.setDateInteg(new Date());
			gm.setRefFichier(dto.getRefFichier());
			gm.setUserInteg(dto.getUserInteg());
		});
		return usersgroupe;
	}

	default List<GroupeModule> dtoToListGroupModuleDto(ModulesGroupsDto dto) {

		List<GroupeModule> groupesModules = listGroupeModuleDtoToListGroupeModule(dto.getGroups());
		groupesModules.forEach(gm -> {
			gm.setDateInteg(new Date());
			gm.setDateDebut(new Date());
			gm.setRefFichier(dto.getRefFichier());
			gm.setUserInteg(dto.getUserInteg());
		});
		return groupesModules;
	}

}
