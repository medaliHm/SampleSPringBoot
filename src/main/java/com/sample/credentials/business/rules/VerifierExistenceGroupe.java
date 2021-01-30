package com.sample.credentials.business.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.sample.credentials.common.config.annotation.IControleMetier;
import com.sample.credentials.common.config.exception.ControleMetierException;
import com.sample.credentials.common.config.service.SpringBeanUtil;
import com.sample.credentials.dao.entity.UserGroup;
import com.sample.credentials.dao.repository.GroupModuleRepository;




public class VerifierExistenceGroupe implements IControleMetier {

	@Override
	public void validate(Object data) throws ControleMetierException {
		GroupModuleRepository groupeRepo = SpringBeanUtil.getBean(GroupModuleRepository.class);

		List<UserGroup> users = (List) data;
		List<String> groups = users.stream().map(x -> x.getId().getLibelleGroupe()).collect(Collectors.toList());
		List<ControleMetierException> controles = new ArrayList<>();
		groups.stream().forEach(code -> {
			if (!groupeRepo.getValideGroupe(code)) {
				ControleMetierException exception = new ControleMetierException("Groupe " + code + " est inexistante",
						"GROUPE INEXISTANT");
				controles.add(exception);
			}
		});
		if (CollectionUtils.isNotEmpty(controles)) {
			throw new ControleMetierException(controles);
		}

	}

}
