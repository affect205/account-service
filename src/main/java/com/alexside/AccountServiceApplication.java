package com.alexside;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Spring boot + oauth2 tutorial:
 * https://gigsterous.github.io/engineering/2017/03/01/spring-boot-4.html
 */
@EnableResourceServer
@SpringBootApplication
public class AccountServiceApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		new AccountServiceApplication()
				.configure(new SpringApplicationBuilder(AccountServiceApplication.class))
				.run(args);
	}
}
