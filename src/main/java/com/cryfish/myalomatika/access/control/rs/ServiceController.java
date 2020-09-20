package com.cryfish.myalomatika.access.control.rs;

import com.cryfish.myalomatika.access.control.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ServiceController {
    @Autowired
    private Environment env;

    @Autowired
    private ServiceConfiguration configuration;

    @GetMapping("/echo")
    public String echo(@RequestParam(value = "value", defaultValue = "World") String value) {
        return value;
    }

    @GetMapping("/profile")
    public String profile() {
        return Arrays.stream(env.getActiveProfiles()).findFirst().orElse("unspecified");
    }

    @GetMapping("/foo")
    public String foo() {
        return configuration.getFoo();
    }
}