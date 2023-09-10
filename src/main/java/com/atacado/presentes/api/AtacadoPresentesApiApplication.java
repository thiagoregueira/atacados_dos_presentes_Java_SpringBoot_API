package com.atacado.presentes.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.mail.MessagingException;

@SpringBootApplication
public class AtacadoPresentesApiApplication {

	public static void main(String[] args) throws MessagingException {
		SpringApplication.run(AtacadoPresentesApiApplication.class, args);
	}

}
