package com.sample.credentials.api.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.credentials.api.adapters.HabilitationAdaptateur;
import com.sample.credentials.api.dto.GroupModuleDtoSortant;
import com.sample.credentials.api.dto.ModulesGroupsDto;
import com.sample.credentials.api.dto.UserGroupDto;
import com.sample.credentials.api.dto.UsersGroupDto;
import com.sample.credentials.common.config.exception.ExceptionMetier;
import com.sample.credentials.common.config.versionning.ApiVersion;
import com.sample.credentials.dao.entity.RftCodeModule;
import com.sample.credentials.dao.repository.RftCodeModuleRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@ApiVersion("1")
@RequestMapping("/habilitations")
public class HabilitationController {

	@Autowired
	private HabilitationAdaptateur habilitationAdaptateur;
	@Autowired
	RftCodeModuleRepository moduleRepository;

	@GetMapping("/groupes")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ApiOperation(notes = "Recupertaion des la liste des groupes et modules", value = "")
	public ResponseEntity<List<GroupModuleDtoSortant>> getAllGroupes() {

		return new ResponseEntity<List<GroupModuleDtoSortant>>(habilitationAdaptateur.getGroupesEtModules(),
				HttpStatus.OK);
	}

	@GetMapping("/modules")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ApiOperation(notes = "Recupertaion des la liste des groupes et modules", value = "")
	public ResponseEntity<List<RftCodeModule>> GetAllModules() {

		return new ResponseEntity<List<RftCodeModule>>(moduleRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/groupes/search")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ApiOperation(notes = "Recupertaion des la liste des groupes et modules", value = "")
	public ResponseEntity<List<GroupModuleDtoSortant>> getGroupeModules(@RequestParam("libGroupe") String libGroupe) {

		return new ResponseEntity<List<GroupModuleDtoSortant>>(
				habilitationAdaptateur.getGroupesEtModulesByLibGroupe(libGroupe), HttpStatus.OK);
	}

	@PostMapping("/groupes/add")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ApiOperation(notes = "Inserer une liste de groupe et leur modules", value = "")
	public ResponseEntity<List<GroupModuleDtoSortant>> insererGroupes(@RequestBody @Valid ModulesGroupsDto dto)
			throws ExceptionMetier {

		return new ResponseEntity<List<GroupModuleDtoSortant>>(habilitationAdaptateur.insererGroupeEtModule(dto),
				HttpStatus.OK);

	}

	@PostMapping("/users/add")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ApiOperation(notes = "Inserer une liste de users et leur grupes", value = "")
	public ResponseEntity<List<UserGroupDto>> insererUsers(@RequestBody @Valid UsersGroupDto dto)
			throws ExceptionMetier {

		return new ResponseEntity<List<UserGroupDto>>(habilitationAdaptateur.insererUsersEtGroupes(dto), HttpStatus.OK);

	}

	@GetMapping("/users")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ApiOperation(notes = "Recuperation de la liste de users et leur groupes", value = "")
	public ResponseEntity<List<UserGroupDto>> getActifsUsers() throws ExceptionMetier {

		return new ResponseEntity<List<UserGroupDto>>(habilitationAdaptateur.getActifUsers(), HttpStatus.OK);

	}

	@GetMapping("/users/current")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@ApiOperation(notes = "Recuperation de la liste de users et leur groupes", value = "")
	public ResponseEntity<UserGroupDto> getConnectedUserGroupe(@RequestParam("idCnp") String idCnp)
			throws ExceptionMetier {
		Optional<UserGroupDto> reponse = habilitationAdaptateur.getActifUsers().stream()
				.filter(user -> user.getIdCnp().equals(idCnp)).findAny();
		if (reponse.isPresent()) {
			return new ResponseEntity<UserGroupDto>(reponse.get(), HttpStatus.OK);

		}

		return new ResponseEntity<UserGroupDto>(new UserGroupDto(), HttpStatus.OK);

	}
}
