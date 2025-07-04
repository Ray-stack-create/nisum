package com.kiit.junit;

import org.springframework.boot.SpringApplication;

public class TestJunitApplication {

	public static void main(String[] args) {
		SpringApplication.from(JunitApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
