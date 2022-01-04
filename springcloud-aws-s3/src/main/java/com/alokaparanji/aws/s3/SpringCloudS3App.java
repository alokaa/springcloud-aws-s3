package com.alokaparanji.aws.s3;

import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.alokaparanji.aws.s3.handler.S3EventHandlerFunction;
import com.amazonaws.services.lambda.runtime.events.S3Event;

//@SpringBootApplication
//@ComponentScan(basePackages = "com.alokaparanji.*")
@SpringBootConfiguration
public class SpringCloudS3App {

  @Autowired
  private S3EventHandlerFunction s3EventHandlerFunction;

  public static void main(String[] args) {
    SpringApplication.run(SpringCloudS3App.class, args);
  }

  @Bean
  public Function<S3Event, String> s3EventHandler() {
    return s3EventHandlerFunction;
  }



}
