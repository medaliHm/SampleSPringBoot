package com.sample.credentials.common.config.versionning;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class CustomWebConfiguration extends DelegatingWebMvcConfiguration {

	@Override
	protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
		return customMappingVersioning();
	}

	public CustomRequestMappingHandlerMapping customMappingVersioning() {
		CustomRequestMappingHandlerMapping handler = new CustomRequestMappingHandlerMapping();
		handler.setOrder(-1);
		return handler;
	}

	@Override
	protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		super.configureAsyncSupport(configurer);
	}
}
