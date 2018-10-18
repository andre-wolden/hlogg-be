package com.hlogg.hloggbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.logging.Logger;

@SpringBootApplication
public class HloggBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HloggBeApplication.class, args);


		LoffBladeRepo.readBlades();
	}
}
