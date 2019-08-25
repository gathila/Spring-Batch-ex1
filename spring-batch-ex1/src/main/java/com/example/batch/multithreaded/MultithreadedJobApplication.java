/*
 * package com.example.batch.multithreaded;
 * 
 * import javax.transaction.Transaction;
 * 
 * import org.springframework.batch.core.Job; import
 * org.springframework.batch.core.Step; import
 * org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
 * import
 * org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
 * import org.springframework.batch.item.database.JdbcBatchItemWriter; import
 * org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean;
 * 
 * public class MultithreadedJobApplication {
 * 
 * @Autowired private JobBuilderFactory jobBuilderFactory;
 * 
 * @Autowired private StepBuilderFactory stepBuilderFactory;
 * 
 * @Bean public Job multitheadedJob() { return
 * this.jobBuilderFactory.get("multitheadedJob") .start(step1()) .build(); }
 * 
 * @Bean public Step step1() { return this.stepBuilderFactory.get("step1")
 * .<Transaction, Transaction>chunk( 100 ) .reader(fileTransactionReader(null))
 * .writer(writer(null)) .build(); }
 * 
 * public JdbcBatchItemWriter<Transaction> write(DataSource dataSource) { return
 * JdbcBatchItemWriterBuilder<Transaction> } }
 */