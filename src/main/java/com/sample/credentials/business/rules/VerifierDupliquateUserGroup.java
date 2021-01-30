package com.sample.credentials.business.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.sample.credentials.common.config.annotation.IControleMetier;
import com.sample.credentials.common.config.exception.ControleMetierException;
import com.sample.credentials.common.config.logger.NormalLogger;
import com.sample.credentials.dao.entity.UserGroup;





public class VerifierDupliquateUserGroup implements IControleMetier {

	@Override
	public void validate(Object data) throws ControleMetierException {
		List<UserGroup> users = (List) data;
		List<ControleMetierException> controles = new ArrayList<>();

		Map<Object, List<UserGroup>> map = users.stream().collect(Collectors.groupingBy(ug -> ug.getUser().getId()));
		map.entrySet().stream().forEach(entry -> {
			NormalLogger.BUSINESS_LOG.log(((String) entry.getKey()) + " ");
			NormalLogger.BUSINESS_LOG.log(entry.getValue().size() + "");

			if (entry.getValue().size() > 1) {
				ControleMetierException exception = new ControleMetierException(
						"L'utilisateur " + entry.getValue().get(0).getUser().getId()
								+ " est affecté à plusieurs groupes en même temps",
						"USER_GROUP_CONSTRAINT");
				controles.add(exception);
			}

		});
		if (CollectionUtils.isNotEmpty(controles)) {
			throw new ControleMetierException(controles);
		}

	}

}
