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

import bap.jp.thanhbn.web_mvc.batch.reader.DeleteProductReader;
import bap.jp.thanhbn.web_mvc.batch.reader.DeleteUserReader;
import bap.jp.thanhbn.web_mvc.batch.writer.DeleteProductWriter;
import bap.jp.thanhbn.web_mvc.batch.writer.DeleteUserWriter;
import bap.jp.thanhbn.web_mvc.model.Product;
import bap.jp.thanhbn.web_mvc.model.User;

@Configuration
@EnableBatchProcessing
public class ClearDataConfig {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private PlatformTransactionManager  platformTransactionManager;
	
	@Autowired
	private DeleteUserReader deleteUserReader;
	
	@Autowired
	private DeleteUserWriter deleteUserWriter;
	
	@Autowired
	private DeleteProductReader deleteProductReader;
	
	@Autowired
	private DeleteProductWriter deleteProductWriter;
	
	@Bean
	public Step deleteUserStep() {
		return new StepBuilder("deleteUser", jobRepository)
				.<User,User>chunk(5,platformTransactionManager)
				.reader(deleteUserReader)
				.writer(deleteUserWriter)
				.faultTolerant()
				.retry(Exception.class)
				.retryLimit(3)
				.build();
	}
	
	@Bean
	public Step deleteProductStep() {
		return new StepBuilder("deleteProduct", jobRepository)
				.<Product,Product>chunk(5, platformTransactionManager)
				.reader(deleteProductReader)
				.writer(deleteProductWriter)
				.faultTolerant()
				.retry(Exception.class)
				.retryLimit(3)
				.build();
				
	}
	
	@Bean(name = "clearDataJobUserAndProduct")
	public Job clearDataJob() {
		return new JobBuilder("clearData", jobRepository)
				.start(deleteProductStep())
				.next(deleteUserStep())
				.preventRestart()
				.build();
	}
	
}
