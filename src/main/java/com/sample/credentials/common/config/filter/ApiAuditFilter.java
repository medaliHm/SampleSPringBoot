package com.sample.credentials.common.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.sample.credentials.common.config.logger.NormalLogger;


@Component
public class ApiAuditFilter implements  Filter {

    /**
     * (methode de remplacement) {@inheritDoc}
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     *      javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requeteURIavecParams = retrouverURIavecParametres(httpRequest);
        String requestVerbe = httpRequest.getMethod();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        NormalLogger.BUSINESS_LOG.log(
                String.format("%s %s %s", "Debut Appel ",requestVerbe, requeteURIavecParams));
        chain.doFilter(httpRequest, httpResponse);

        stopWatch.stop();
        NormalLogger.BUSINESS_LOG.log(
                String.format("%s %s %s", "Fin Appel ",requestVerbe, requeteURIavecParams));


        NormalLogger.API_AUDIT_LOG.log(
        String.format("%s|%s|%d|%d ms", requestVerbe, requeteURIavecParams, httpResponse.getStatus(),
        stopWatch.getTotalTimeMillis()));



    }

    /**
     * methode Retrouver UR iavec parametres : DOCUMENTEZ_MOI.
     *
     * @param httpRequest DOCUMENTEZ_MOI
     * @return string
     */
    private static String retrouverURIavecParametres(HttpServletRequest httpRequest)
    {
        StringBuilder requeteURI = new StringBuilder();
        requeteURI.append(httpRequest.getRequestURI());
        String requeteParams = httpRequest.getQueryString();

        if (requeteParams != null)
        {
            requeteURI.append("?").append(requeteParams);
        }

        return requeteURI.toString();
    }
}
