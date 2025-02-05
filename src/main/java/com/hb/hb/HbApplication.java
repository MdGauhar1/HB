package com.hb.hb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hb.hb")
public class HbApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbApplication.class, args);
	}

}
