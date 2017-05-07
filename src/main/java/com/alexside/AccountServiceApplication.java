package com.alexside;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class AccountServiceApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		new AccountServiceApplication()
				.configure(new SpringApplicationBuilder(AccountServiceApplication.class))
				.run(args);
	}
}
