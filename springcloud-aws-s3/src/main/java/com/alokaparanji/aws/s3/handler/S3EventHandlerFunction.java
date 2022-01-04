package com.alokaparanji.aws.s3.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alokaparanji.aws.s3.model.Person;
import com.alokaparanji.aws.s3.service.PersonService;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class S3EventHandlerFunction {

	@Autowired
	private PersonService personService;

	@Bean
	public Function<S3Event, String> s3EventHandler() {
		return (s3Event) -> {
			s3Event.getRecords().forEach(event -> {
				AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
				S3Object s3Object = s3Client.getObject(
						new GetObjectRequest(event.getS3().getBucket().getName(), event.getS3().getObject().getKey()));
				InputStream objectData = s3Object.getObjectContent();
				ObjectMapper mapper = new ObjectMapper();
				try {
					Person[] persons = mapper.readValue(objectData, Person[].class);
					personService.printService(persons);
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			return "File Processed Successfully";

		};
	}

}
