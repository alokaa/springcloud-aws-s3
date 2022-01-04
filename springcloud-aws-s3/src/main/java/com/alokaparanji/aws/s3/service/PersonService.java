package com.alokaparanji.aws.s3.service;

import java.util.Arrays;
import org.springframework.stereotype.Component;
import com.alokaparanji.aws.s3.model.Person;

@Component
public class PersonService {

	public void printService(Person[] persons) {
		Arrays.stream(persons).forEach(System.out::println);
	}

}
