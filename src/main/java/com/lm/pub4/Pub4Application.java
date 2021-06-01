package com.lm.pub4;

import com.lm.pub4.Services.CLIProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Pub4Application {

	public static void main(String[] args) {
		SpringApplication.run(Pub4Application.class, args);
		CLIProcessor cliProcessor = new CLIProcessor();
		cliProcessor.run();

	}
}
