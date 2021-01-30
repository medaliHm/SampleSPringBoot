package com.sample.credentials.business.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.sample.credentials.common.config.annotation.IControleMetier;
import com.sample.credentials.common.config.exception.ControleMetierException;
import com.sample.credentials.common.config.service.SpringBeanUtil;
import com.sample.credentials.dao.entity.GroupeModule;
import com.sample.credentials.dao.repository.RftCodeModuleRepository;




public class VerifierExistenceCdModule implements IControleMetier {

	@Override
	public void validate(Object data) throws ControleMetierException {

		RftCodeModuleRepository moduleRepository = SpringBeanUtil.getBean(RftCodeModuleRepository.class);
		List<GroupeModule> group = (List) data;
		List<Long> cdsModule = group.stream().map(x -> x.getModule().getCodeModule()).collect(Collectors.toList());
		List<ControleMetierException> controles = new ArrayList<>();
		cdsModule.stream().forEach(code -> {
			if (!moduleRepository.existsById(code)) {
				ControleMetierException exception = new ControleMetierException(
						"Le code Module " + code + " n'existe pas dans la table Module", "MODULE_INEXISTANT");
				controles.add(exception);
			}
		});
		if (CollectionUtils.isNotEmpty(controles)) {
			throw new ControleMetierException(controles);
		}

	}

	@Override
	public boolean isBloquant() {
		return false;
	}

}
