package com.lta.springbatch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class EmployeeItemProcessor implements ItemProcessor<String, Employee> {

	private static final Logger log = LoggerFactory.getLogger(EmployeeItemProcessor.class);

	@Override
	public Employee process(String employee) {
		
		String firstName = employee.split("\\s+")[0];
		String lastName = employee.split("\\s+")[1];
		
		Employee processedItem = new Employee(firstName, lastName);

		log.info("Converting (" + employee + ") into (" + processedItem + ")");

		return processedItem;
	}

}