package com.sample.credentials.common.config.filter;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class FiltresHTTPConfig Enregistrement des filtres HTTP sur tous les appels vers /api/* : ces filtres permettent de
 * logger les requêtes, enrichir les headers, etc
 */
@Configuration
public class FiltresHTTPConfig
{

    /** version api. */
    private String versionApi="0.0.1";

    /** Constant : URL_BASE. */
    private static final String URL_BASE = "/api/*";

    /**
     * Constructeur de la classe FiltresHTTPConfig.java
     *
     */
    public FiltresHTTPConfig()
    {
        super();
    }

    /**
     * methode Mdc filtre : DOCUMENTEZ_MOI.
     *
     * @return filter registration bean
     */
    @Bean
    public FilterRegistrationBean mdcFiltre()
    {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new HeaderFilter(versionApi));
        registrationBean.addUrlPatterns(URL_BASE);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    /**
     * methode Api audit filtre : DOCUMENTEZ_MOI.
     *
     * @return filter registration bean
     */
    @Bean
    public FilterRegistrationBean apiAuditFiltre()
    {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ApiAuditFilter());
        registrationBean.addUrlPatterns(URL_BASE);
        registrationBean.setOrder(2);
        return registrationBean;
    }

}
