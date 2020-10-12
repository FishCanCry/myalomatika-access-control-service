package com.cryfish.springboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestLoggingFilter extends AbstractRequestLoggingFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    public RequestLoggingFilter() {
        setIncludeQueryString(true);
        setIncludePayload(true);
        setMaxPayloadLength(100000);
        setIncludeHeaders(true);
        setIncludeClientInfo(true);
        setBeforeMessagePrefix("Got request: ");
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info(message);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long timestamp = System.currentTimeMillis();

        super.doFilterInternal(request, response, filterChain);

        logger.info("Process {} request '{}' in {} ms", request.getMethod(), request.getRequestURI(), System.currentTimeMillis() - timestamp);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

    }
}
