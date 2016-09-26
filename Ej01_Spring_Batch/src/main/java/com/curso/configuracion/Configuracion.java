package com.curso.configuracion;
import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;
 
@Configuration
@ComponentScan(basePackages={ "com.curso" })
@EnableBatchProcessing
//JobRepository ("jobRepository")
//JobLauncher ("jobLauncher")
//JobRegistry ("jobRegistry")
//PlatformTransactio8nManager ("transactionManager")
//JobBuilderFactory jobBuilders")
//StepBuilderFactory ("stepBuilders")
public class Configuracion {
 
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setDriverClassName("org.h2.Driver");
		dmds.setUrl("jdbc:h2:file:C:/H2/bbdd");
		dmds.setUsername("sa");
		dmds.setPassword("");
		return dmds;
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }	
	
}

