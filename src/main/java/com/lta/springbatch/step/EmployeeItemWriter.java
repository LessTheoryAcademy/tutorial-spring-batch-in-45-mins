package com.lta.springbatch.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class EmployeeItemWriter implements ItemWriter<Employee> {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeItemWriter.class);
	
	@Override
	public void write(List<? extends Employee> items) {
		
		for ( Employee item : items ) {
			log.info("Writing to console : " + item );
		}
	}
}
