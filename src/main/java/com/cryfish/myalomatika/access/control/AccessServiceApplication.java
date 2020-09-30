package com.cryfish.myalomatika.access.control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cryfish")
public class AccessServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccessServiceApplication.class, args);
	}
}
