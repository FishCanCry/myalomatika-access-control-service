package com.cryfish.springboot.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class RestTemplate extends org.springframework.web.client.RestTemplate {
    public RestTemplate() {
        setInterceptors(Collections.singletonList(new CorrelationIdInterceptor()));
    }
}
