package com.sample.credentials.common.config.filter;

import java.io.IOException;
import java.net.InetAddress;
import java.util.UUID;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;

import com.sample.credentials.common.config.logger.NormalLogger;

public class HeaderFilter implements Filter {



    /** version api. */
    private final String versionApi;

    /**
     * Instanciation de headers filter.
     * @param versionApi
     */
    public HeaderFilter(String versionApi)
    {
        super();
        this.versionApi = versionApi;
    }

    /**
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String correlationId = httpRequest.getHeader("X-Correlation-ID");

        if (!currentRequestIsAsyncDispatcher(httpRequest)) {
            if (correlationId == null) {
                correlationId = UUID.randomUUID().toString();
                NormalLogger.API_IO_LOG.log("No correlationId found in Header. Generated : " + correlationId);
            } else {
                NormalLogger.API_IO_LOG.log("Found correlationId in Header : " + correlationId);

            }

        }
        String hostname = InetAddress.getLocalHost().getHostName();
        String sessionId = httpRequest.getSession().getId();

        MDC.put("X-CORRELATION-ID", correlationId);
        MDC.put("X-SESSION-ID", sessionId);
        MDC.put("X-VERSION", versionApi);
        MDC.put("X-HOST-NAME", hostname);


        chain.doFilter(httpRequest, httpResponse);

    }
    private boolean currentRequestIsAsyncDispatcher(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getDispatcherType().equals(DispatcherType.ASYNC);
    }

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        HttpHeaders headers = exchange.getRequest().getHeaders();
//        return chain.filter(exchange)
//                .doAfterSuccessOrError((r, t) -> logWithContext(headers, httpHeaders -> NormalLogger.API_IO_LOG.log("")))
//                .subscriberContext(Context.of(HttpHeaders.class, headers));
//    }

//    static void logWithContext(HttpHeaders headers, Consumer<HttpHeaders> logAction) {
//        try {
//            headers.forEach((name, values) -> MDC.put(name, values.get(0)));
//            logAction.accept(headers);
//        } finally {
//            headers.keySet().forEach(MDC::remove);
//        }
//
//    }
}
