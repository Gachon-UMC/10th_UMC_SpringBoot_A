package com.umc10th.umc10th_kamang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Umc10thKamangApplication {

	public static void main(String[] args) {
		SpringApplication.run(Umc10thKamangApplication.class, args);
	}

}
