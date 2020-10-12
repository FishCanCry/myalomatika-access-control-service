package com.cryfish.springboot.rest;

import com.cryfish.springboot.filter.CorrelationFilter;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class CorrelationIdInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add(CorrelationFilter.CORRELATION_ID_HEADER_NAME, MDC.get(CorrelationFilter.MDC_CORRELATION_ID_NAME));
        return execution.execute(request, body);
    }
}
