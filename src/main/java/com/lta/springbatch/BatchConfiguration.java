package com.lta.springbatch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lta.springbatch.step.Employee;
import com.lta.springbatch.step.EmployeeItemProcessor;
import com.lta.springbatch.step.EmployeeItemReader;
import com.lta.springbatch.step.EmployeeItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job importEmployeesJob(JobExecutionListener listener, Step step1) {
	  return jobBuilderFactory.get("importEmployeesJob")
	    .incrementer(new RunIdIncrementer())
	    .listener(listener)
	    .start(step1)
	    .build();
	}	
	
	@Bean
	public Step step1(JdbcBatchItemWriter<Employee> writerStep1) {
	  return stepBuilderFactory.get("step1")
	    .<String, Employee> chunk(5)
	    .reader(readerStep1())
	    .processor(processorStep1())
	    .writer(writerStep1)
	    .build();
	}
	
	@Bean
	public EmployeeItemReader readerStep1() {
		
		return new EmployeeItemReader();
	}
	
	@Bean
	public EmployeeItemProcessor processorStep1() {
		
		return new EmployeeItemProcessor();
	}
	
	/*
	@Bean
	public EmployeeItemWriter writerStep1() {
		
		return new EmployeeItemWriter();
	}
	*/
	
	@Bean
	public JdbcBatchItemWriter<Employee> writerStep1(DataSource dataSource) {
	  return new JdbcBatchItemWriterBuilder<Employee>()
	    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	    .sql("INSERT INTO employees (firstname, lastname) VALUES (:firstName, :lastName)")
	    .dataSource(dataSource)
	    .build();
	}	
}
