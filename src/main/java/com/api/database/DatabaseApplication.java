package com.api.database;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.api.database.customer.Customer;

@SpringBootApplication
public class DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

	@Bean
	List<Customer> wallets() {
		return new ArrayList<>();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
