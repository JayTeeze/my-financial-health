package com.jayteeze.myfinancialhealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.jayteeze")
public class MyFinancialHealthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFinancialHealthApplication.class, args);
	}

}
