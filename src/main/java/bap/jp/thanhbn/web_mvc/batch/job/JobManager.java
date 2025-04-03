package bap.jp.thanhbn.web_mvc.batch.job;

import java.util.Set;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class JobManager {
	
	@Autowired
	private JobLauncher joblaucher;
	
	@Autowired
	@Qualifier("exportOrderJob")
	private Job jobExportOrder;
	
	@Autowired
	@Qualifier("importProductJob")
	private Job jobImportProduct;
	
	@Autowired
	@Qualifier("clearDataJobUserAndProduct")
	private Job jobClearData;
	
	@Autowired
	private JobExplorer jobExplorer;
	
	@Autowired
	private JobOperator jobOperator;
	
    public boolean isAcionImportRunning = true;
    public boolean isAcionExportRunning = true;
    public boolean isAcionClearRunning = true; 
	
	@Scheduled(cron = "0 0 * * * *")
	//@Scheduled(cron = "*/5 * * * * *")
	public void runJobExportOrder() {
		if(isAcionExportRunning) {
			runJob("exportOrder", jobExportOrder);
		}
	}
	
	//@Scheduled(cron = "0 5 * * * *")
	@Scheduled(cron = "*/3 * * * * *")
	public void runJobImportProduct() {
		if(isAcionImportRunning) {
			runJob("importProduct", jobImportProduct);		

		}
	}
	
	@Scheduled(cron = "0 1 0 1 * *")
	//@Scheduled(cron = "*/9 * * * * *")
	public void runJobClearData() {
		if(isAcionClearRunning) {
			runJob("jobClearDataUserAndProduct", jobClearData);

		}
	}
	
	public void runJob(String name, Job job) {
		Set<JobExecution> excutions = jobExplorer.findRunningJobExecutions(name);
		
		if(!excutions.isEmpty()) {
			return;
		}		
		
		JobParameters jobparameter = new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis())
				.toJobParameters();
		
		try {
			JobExecution excution = joblaucher.run(job, jobparameter);
			System.out.println("job "+name+" dang chay");
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void stopJob(String name) {
		Set<JobExecution> excutions =  jobExplorer.findRunningJobExecutions(name);

		for (JobExecution jobExecution : excutions) {
			try {
				System.out.println("name id job : " +jobExecution.getId());
				jobOperator.stop(jobExecution.getId());
				
			} catch (NoSuchJobExecutionException | JobExecutionNotRunningException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Job 2" + name +" da dung");

	}
	
	public void stopAllJob() {
		stopJob("exportOrder");
		stopJob("importProduct");
		stopJob("jobClearDataUserAndProduct");
	}
}
