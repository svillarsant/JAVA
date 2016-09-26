import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.curso.configuracion.Configuracion;
 
public class Pruebas {
 
    public static void main(String[] args) throws Exception {
    	
		AnnotationConfigApplicationContext appCtx = 
			new AnnotationConfigApplicationContext(Configuracion.class);
		
        JobLauncher jobLauncher = appCtx.getBean(JobLauncher.class);
           
        /*
    	Job jobCSVaCSV = appCtx.getBean("CochesCSVaCSV", Job.class);
    	JobParameters jobParameters = new JobParametersBuilder()
		.addDate("date", new Date())
		.toJobParameters();        	
       	JobExecution jobExecution = jobLauncher.run(jobCSVaCSV, jobParameters); //jobParameters);

    	BatchStatus batchStatus = jobExecution.getStatus();
    	while(batchStatus.isRunning()){
    		System.out.println("Ejecutando...");
    		Thread.sleep(1000);
    	}
    	System.out.println("Estado:"+ jobExecution.getExitStatus().getExitCode());
    	JobInstance jobInstance = jobExecution.getJobInstance();
    	System.out.println("Nombre:"+ jobInstance.getJobName());
    	System.out.println("Id: "+ jobInstance.getId());
    	
    	
    	Thread.sleep(1000);    	
    	
    	Job cochesXMLaXML = appCtx.getBean("CochesXMLaXML", Job.class);
    	JobParameters jobParameters2 = new JobParametersBuilder()
		.addDate("date", new Date())
		.toJobParameters();    
    	
       	JobExecution jobExecution2 = jobLauncher.run(cochesXMLaXML, jobParameters2);

    	BatchStatus batchStatus2 = jobExecution2.getStatus();
    	while(batchStatus2.isRunning()){
    		System.out.println("Ejecutando...");
    		Thread.sleep(1000);
    	}
    	System.out.println("Estado:"+ jobExecution2.getExitStatus().getExitCode());
    	JobInstance jobInstance2 = jobExecution2.getJobInstance();
    	System.out.println("Nombre:"+ jobInstance2.getJobName());
    	System.out.println("Id: "+ jobInstance2.getId());


    	
    	Thread.sleep(1000);    	
    	
    	Job cochesBBDDaBBDD = appCtx.getBean("CochesBBDDaBBDD", Job.class);
    	JobParameters jobParameters3 = new JobParametersBuilder()
		.addDate("date", new Date())
		.toJobParameters();    
    	
       	JobExecution jobExecution3 = jobLauncher.run(cochesBBDDaBBDD, jobParameters3);

    	BatchStatus batchStatus3 = jobExecution3.getStatus();
    	while(batchStatus3.isRunning()){
    		System.out.println("Ejecutando...");
    		Thread.sleep(1000);
    	}
    	System.out.println("Estado:"+ jobExecution3.getExitStatus().getExitCode());
    	JobInstance jobInstance3 = jobExecution3.getJobInstance();
    	System.out.println("Nombre:"+ jobInstance3.getJobName());
    	System.out.println("Id: "+ jobInstance3.getId());    	
    	
    	
    	Thread.sleep(1000);    	
    	
    	Job cochesRESTaConsola = appCtx.getBean("CochesRESTaConsola", Job.class);
    	JobParameters jobParameters4 = new JobParametersBuilder()
    			.addDate("date", new Date())
    			.toJobParameters();    
    	
    	JobExecution jobExecution4 = jobLauncher.run(cochesRESTaConsola, jobParameters4);
    	
    	BatchStatus batchStatus4 = jobExecution4.getStatus();
    	while(batchStatus4.isRunning()){
    		System.out.println("Ejecutando...");
    		Thread.sleep(1000);
    	}
    	System.out.println("Estado:"+ jobExecution4.getExitStatus().getExitCode());
    	JobInstance jobInstance4 = jobExecution4.getJobInstance();
    	System.out.println("Nombre:"+ jobInstance4.getJobName());
    	System.out.println("Id: "+ jobInstance4.getId());    	
    	
    	
    	Thread.sleep(1000);    	
    	
    	Job cochesCSVaCSVParametros = appCtx.getBean("CochesCSVaCSVParametros", Job.class);
    	JobParameters jobParameters5 = new JobParametersBuilder()
    			.addString("ficheroEntrada", "datos/entrada.csv")
    			.addString("ficheroSalida", "e:/salida3.csv")
    			.addDate("date", new Date())
    			.toJobParameters();    
    	
    	JobExecution jobExecution5 = jobLauncher.run(cochesCSVaCSVParametros, jobParameters5);
    	
    	BatchStatus batchStatus5 = jobExecution5.getStatus();
    	while(batchStatus5.isRunning()){
    		System.out.println("Ejecutando...");
    		Thread.sleep(1000);
    	}
    	System.out.println("Estado:"+ jobExecution5.getExitStatus().getExitCode());
    	JobInstance jobInstance5 = jobExecution5.getJobInstance();
    	System.out.println("Nombre:"+ jobInstance5.getJobName());
    	System.out.println("Id: "+ jobInstance5.getId());    	
		*/
    	
    	Thread.sleep(1000);    	
    	
    	Job cochesCSVaCSVOyente = appCtx.getBean("CochesCSVaCSVListener", Job.class);
    	JobParameters jobParameters6 = new JobParametersBuilder()
    			.addDate("date", new Date())
    			.toJobParameters(); 
    	
    	JobExecution jobExecution6 = jobLauncher.run(cochesCSVaCSVOyente, jobParameters6);
    	
    	
    }
 
}