package com.cryfish.springboot.filter;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorrelationFilter extends OncePerRequestFilter {
    public static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-ID";
    public static final String MDC_CORRELATION_ID_NAME = "CorrelationId";
    private static final int CORRELATION_ID_LENGTH = 10;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            MDC.put(MDC_CORRELATION_ID_NAME, getCorrelationId(request));
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(MDC_CORRELATION_ID_NAME);
        }
    }

    private String getCorrelationId(HttpServletRequest request) {
        String correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);

        if(StringUtils.isEmpty(correlationId)) {
            return RandomStringUtils.random(CORRELATION_ID_LENGTH, true, true);
        } else {
            return correlationId;
        }
    }
}
