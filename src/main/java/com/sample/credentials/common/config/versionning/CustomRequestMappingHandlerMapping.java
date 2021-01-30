package com.sample.credentials.common.config.versionning;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//import org.springframework.web.reactive.result.condition.PatternsRequestCondition;
//import org.springframework.web.reactive.result.method.RequestMappingInfo;
//import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
//import org.springframework.web.util.pattern.PathPattern;

/**
 * Class CustomRequestMappingHandlerMapping permet de prefixer n'importe quel
 * URL d'un endpoint REST, pour une gestion centralisé du versioning, via
 * annotation ApiVersion
 *
 */
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

	/** Constant : VERSION_PREFIX. */
	private static final String VERSION_PREFIX = "api/v";

	/**
	 * (methode de remplacement) {@inheritDoc}
	 *
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#getMappingForMethod(java.lang.reflect.Method,
	 *      java.lang.Class)
	 *
	 */
	@Override
	protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
		RequestMappingInfo methodMapping = super.getMappingForMethod(method, handlerType);
		if (methodMapping == null) {
			return null;
		}
		String versionPattern = "";

		if (handlerType.isAnnotationPresent(ApiVersion.class)) {
			versionPattern = handlerType.getAnnotation(ApiVersion.class).value();
		}

		if (StringUtils.isNotBlank(versionPattern)) {
			RequestMappingInfo superclassRequestMappingInfo = new RequestMappingInfo("",
					new PatternsRequestCondition(VERSION_PREFIX + versionPattern), null, null, null, null, null, null);
			return superclassRequestMappingInfo.combine(methodMapping);
		} else {
			return methodMapping;
		}
	}

	// a mettre si on passe en full reactive
	//
	// private RequestMappingInfo withPrefix(RequestMappingInfo mapping,String
	// versionPattern) {
	// if (StringUtils.isBlank(versionPattern)) {
	// return mapping;
	// }
	// PatternsRequestCondition patternsCondition = new PatternsRequestCondition(
	// withNewPatterns(mapping.getPatternsCondition().getPatterns()));
	// return new RequestMappingInfo(patternsCondition,
	// mapping.getMethodsCondition(), mapping.getParamsCondition(),
	// mapping.getHeadersCondition(), mapping.getConsumesCondition(),
	// mapping.getProducesCondition(),
	// mapping.getCustomCondition());
	// }
	//
	// private List<PathPattern> withNewPatterns(Set<PathPattern> patterns,String
	// version) {
	// return patterns.stream()
	// .map((pattern) ->
	// getPathPatternParser().parse(normalizePath(pattern+version)))
	// .collect(toList());
	// }
	//
	// public static String normalizePath(String path) {
	// if (StringUtils.isBlank(path)) {
	// return path;
	// }
	// String normalizedPath = path;
	// if (!normalizedPath.startsWith("/")) {
	// normalizedPath = "/" + normalizedPath;
	// }
	// if (normalizedPath.endsWith("/")) {
	// normalizedPath = normalizedPath.substring(0, normalizedPath.length() - 1);
	// }
	// return normalizedPath;
	// }

}
