package com.curso.batch_2_XML;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.curso.dto.CocheDTO;
 
@Configuration
@EnableBatchProcessing
public class JobConfigXMLaXML {
 
    @Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory steps;
    
    @Bean
    public ItemReader<CocheDTO> readerXMLCoches() {
    	
    	StaxEventItemReader<CocheDTO> reader = new StaxEventItemReader<CocheDTO>();
		reader.setResource(new ClassPathResource("datos/entrada.xml"));
		reader.setFragmentRootElementName("coche");
		
		Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
		unmarshaller.setClassesToBeBound(CocheDTO.class);
		reader.setUnmarshaller(unmarshaller);
		
		return reader;
    }     
    
    @Bean
    public ItemProcessor<CocheDTO, CocheDTO> processorValidacion() {
        return new ProcessorValidacion();
    }    
      
    @Bean
    public ItemWriter<CocheDTO> writerXMLCoches() {
		
    	System.out.println("///////////////////////////////////////");
    	System.out.println("///////////////////////////////////////");
    	System.out.println("///////////////////////////////////////");
    	System.out.println("///////////////////////////////////////");
    	
    	StaxEventItemWriter<CocheDTO> writer = new StaxEventItemWriter<CocheDTO>();
    	
		//writer.setResource(new ClassPathResource("datos/salida.xml"));
    	writer.setResource(new FileSystemResource("e:/salida.xml"));
    	writer.setRootTagName("coche");		
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(CocheDTO.class);
		writer.setMarshaller(marshaller);			
        
		return writer;
    }        
 
    @Bean
    public Job CochesXMLaXML(){
    	return jobs.get("CochesXMLaXML").start(step2()).build();
    }
 
    @Bean
    public Step step2() {
    	return steps.get("step2")
    			.<CocheDTO, CocheDTO> chunk(5)
    			.reader(readerXMLCoches())
    			.processor(processorValidacion())
    			.writer(writerXMLCoches())
    			.build();
    }
}