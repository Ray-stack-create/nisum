package com.kiit.calculator;

import org.springframework.boot.SpringApplication;

public class TestCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.from(CalculatorApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
