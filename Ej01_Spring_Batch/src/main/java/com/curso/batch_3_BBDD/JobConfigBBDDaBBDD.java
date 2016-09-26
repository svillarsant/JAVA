package com.curso.batch_3_BBDD;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.curso.batch_2_XML.ProcessorValidacion;
import com.curso.dto.CocheDTO;
 
@Configuration
@EnableBatchProcessing
public class JobConfigBBDDaBBDD {
 
    @Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory steps;
 
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public ItemReader<CocheDTO> readerBBDDcoches() {
    	JdbcCursorItemReader<CocheDTO> reader = new JdbcCursorItemReader<CocheDTO>();
    	reader.setDataSource(dataSource);
    	reader.setSql("select * from coches");
    	reader.setRowMapper(new BeanPropertyRowMapper<CocheDTO>(CocheDTO.class));
     	
    	return reader;    	
    }     
    
    @Bean
    public ItemProcessor<CocheDTO, CocheDTO> processorValidacion() {
        return new ProcessorValidacion();
    }    
      
    @Bean
    public ItemWriter<CocheDTO> writerBBDDCoches() {
		JdbcBatchItemWriter<CocheDTO> writer = new JdbcBatchItemWriter<CocheDTO>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CocheDTO>());
        writer.setSql("INSERT INTO coches_historico (id,marca,modelo,matricula) VALUES (:id, :marca,:modelo,:matricula)");
        writer.setDataSource(dataSource);   
        return writer;
    }        
 
    @Bean
    public Job CochesBBDDaBBDD(){
    	return jobs.get("CochesBBDDaBBDD").start(step3()).build();
    }
 
    @Bean
    public Step step3() {
    	return steps.get("ccc")
    			.<CocheDTO, CocheDTO> chunk(5)
    			.reader(readerBBDDcoches())
    			.processor(processorValidacion())
    			.writer(writerBBDDCoches())
    			.build();
    }
}