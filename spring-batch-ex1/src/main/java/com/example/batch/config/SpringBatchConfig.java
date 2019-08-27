package com.example.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.example.batch.model.User;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Value("${input}")
	private Resource resource;
	
	@Autowired
	private ItemProcessor<User, User> processor;
	
	@Autowired
	private ItemWriter<User> dbwriter;

	//ETL-JOB is a name of JOB. we can give any name for the Job
	//Incrementer is sequence of ids assign to every run
	//Every time I run a batch, run id increment one by one
	//edit remo dwdw xxxx
	@Bean
	public Job job() {
		
		System.out.println("Job bean creating");
				
		return jobBuilderFactory.get("ETL-Load")
		.incrementer(new RunIdIncrementer())
		.start(step1())
		.build();
	}
	
	@Bean
	public Step step1() {
		
		return stepBuilderFactory.get("ETL-file-load")
				.<User, User>chunk(100)
				.reader(getItemReader())
				.processor(getItemProcessor())
				.writer(getItemWriter())
				.build();
	}
	
	public FlatFileItemReader<User> getItemReader(){
		
		FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<User>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}
	
	@Bean
	public LineMapper<User> lineMapper(){
		DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<User>();
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames(new String [] {"id", "name", "dept", "salary"});
		
		BeanWrapperFieldSetMapper<User> fieldMapper = new BeanWrapperFieldSetMapper<User>();
		fieldMapper.setTargetType(User.class);
		
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldMapper);
		
		return defaultLineMapper;
	}
	
	public ItemProcessor<User, User> getItemProcessor() {
		return this.processor;
	}
	
	public ItemWriter<User> getItemWriter(){
		return this.dbwriter;
	}
}
