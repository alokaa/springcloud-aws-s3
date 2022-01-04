package com.alokaparanji.aws.s3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.alokaparanji.aws")
public class SpringCloudS3App {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudS3App.class, args);
	}


}
