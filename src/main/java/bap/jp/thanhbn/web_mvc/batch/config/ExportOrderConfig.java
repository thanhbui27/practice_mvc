package bap.jp.thanhbn.web_mvc.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import bap.jp.thanhbn.web_mvc.batch.processor.ExportOrderItemProcessor;
import bap.jp.thanhbn.web_mvc.batch.reader.ExportOrderItemReader;
import bap.jp.thanhbn.web_mvc.batch.writer.ExportOrderItemWriter;
import bap.jp.thanhbn.web_mvc.model.Order;

@Configuration
@EnableBatchProcessing
public class ExportOrderConfig {
	 
	@Autowired
	 private PlatformTransactionManager platformTransactionManager;
	
	@Autowired
	private JobRepository jobRepository;
	 
	@Autowired
	private ExportOrderItemReader itemReader;
	
	@Autowired
	private ExportOrderItemProcessor itemProcess;
	
	@Autowired
	private ExportOrderItemWriter itemWriter;
	
	
	@Bean
	public Step stepExportOrderJob() {
		 return new StepBuilder("stepWriteToCsv",jobRepository)
				 .<Order,Order>chunk(3,platformTransactionManager)
				 .reader(itemReader)
				 .processor(itemProcess)
				 .writer(itemWriter)
				 .build();
	 }
	 
	 
	 @Bean(name = "exportOrderJob")
	 public Job exportOrderJob() {
		 return new JobBuilder("jobExportOrder", jobRepository).start(stepExportOrderJob()).build();
	 }
	
	
}
