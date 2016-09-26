package com.curso.batch_6;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class OyenteJob implements JobExecutionListener{

	@Override
	public void afterJob(JobExecution jobExecution) {
		
		if(jobExecution.getStatus() == BatchStatus.COMPLETED){
			System.out.println("OK:"+jobExecution.getJobId());
		} else if(jobExecution.getStatus() == BatchStatus.FAILED){
			System.out.println("MAL"+jobExecution.getJobId());
		}
		
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
	}

}
