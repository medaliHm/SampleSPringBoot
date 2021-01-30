package com.sample.credentials.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.credentials.dao.entity.UserGroup;
import com.sample.credentials.dao.repository.UserGroupRepository;

@Service
public class UserGroupRecuperationService {

	@Autowired
	private UserGroupRepository repo;

	public List<UserGroup> recupererUsersEtGroupes() {

		return repo.recupActifUsers();
	}
}
