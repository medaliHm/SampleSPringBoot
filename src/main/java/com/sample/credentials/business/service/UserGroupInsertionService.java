package com.sample.credentials.business.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.credentials.business.rules.VerifierDupliquateUserGroup;
import com.sample.credentials.business.rules.VerifierExistenceGroupe;
import com.sample.credentials.common.config.annotation.ControleMetier;
import com.sample.credentials.common.config.service.AbstractService;
import com.sample.credentials.dao.entity.UserGroup;
import com.sample.credentials.dao.repository.UserGroupRepository;
import com.sample.credentials.dao.repository.UserInfoRepository;

@Service
@ControleMetier({ VerifierExistenceGroupe.class, VerifierDupliquateUserGroup.class })
public class UserGroupInsertionService extends AbstractService<List<UserGroup>, List<UserGroup>> {

	@Autowired
	UserGroupRepository userRepository;
	@Autowired
	UserInfoRepository userInfoRepository;

	@Transactional
	@Override
	public List<UserGroup> doExecute(List<UserGroup> userGroupToInsert) {

		return userRepository.saveAll(userGroupToInsert);
	}

}
