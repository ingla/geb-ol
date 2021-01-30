package com.example.gebol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
@Slf4j
public class GebOlApplication {

	public static void main(String[] args) {

		log.info("Main method");
		SpringApplication.run(GebOlApplication.class, args);
	}

}
