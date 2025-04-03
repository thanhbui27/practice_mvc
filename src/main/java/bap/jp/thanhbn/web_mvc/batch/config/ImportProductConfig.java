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

import bap.jp.thanhbn.web_mvc.batch.processor.ImportProductProcess;
import bap.jp.thanhbn.web_mvc.batch.reader.ImportProductReader;
import bap.jp.thanhbn.web_mvc.batch.writer.ImportProductWriter;
import bap.jp.thanhbn.web_mvc.model.Product;

@Configuration
@EnableBatchProcessing
public class ImportProductConfig {
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private ImportProductReader itemReader;
	
	@Autowired
	private ImportProductProcess itemProcess;
	
	@Autowired
	private ImportProductWriter itemWriter;
	
	@Bean
	public Step stepImportProduct( ) {
		return new StepBuilder("stepImportProduct", jobRepository)
				.<Product,Product>chunk(5, platformTransactionManager)
				.reader(itemReader)
				.processor(itemProcess)
				.writer(itemWriter)
				.build();
	}
	
	@Bean(name = "importProductJob")
	public Job jobImportProduct() {
		return new JobBuilder("jobImportProduct", jobRepository)
				.start(stepImportProduct()).build();
	}
	
	
	
}
