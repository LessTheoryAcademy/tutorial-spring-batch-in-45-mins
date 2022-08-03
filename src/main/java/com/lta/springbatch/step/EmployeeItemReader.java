package com.lta.springbatch.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

public class EmployeeItemReader implements ItemReader<String> {

	private static final Logger log = LoggerFactory.getLogger(EmployeeItemReader.class);

	private final String[] names = new String[] { 
			"Luca Rossi", 
			"Paolo Verdi", 
			"Giovanni Bianchi", 
			"Luca Viola",
			"Filippo Rosa" 
	};
	
	private int index = 0;

	@Override
	public String read() {

		if (index < names.length) {

			String name = names[index];

			log.info("Reading next item " + name);
			
			index++;

			return name;
			
		} else {
			
			return null;
		}
	}

}