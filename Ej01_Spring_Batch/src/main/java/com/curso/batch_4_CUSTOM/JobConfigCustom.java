package com.curso.batch_4_CUSTOM;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.curso.batch_1_CSV.ProcessorMayusculas;
import com.curso.batch_4_CUSTOM.WriterConsolaCoches;
import com.curso.dto.CocheDTO;
 
@Configuration
@EnableBatchProcessing
public class JobConfigCustom {
 
    @Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory steps;
    
    @Bean
    public ItemReader<CocheDTO> readerRESTCoches() {
    	ReaderRESTCoches reader = new ReaderRESTCoches("http://localhost:8080/Ej09_SpringBatch_ServicioREST/servicio/coches");
        return reader;
    }     
    
    @Bean
    public ItemProcessor<CocheDTO, CocheDTO> processorMayusculas() {
        return new ProcessorMayusculas();
    }    
        
    @Bean
    public ItemWriter<CocheDTO> writerConsolaCoches() {
    	WriterConsolaCoches writer = new WriterConsolaCoches();
		return writer;
    }        
 
    @Bean
    public Job CochesRESTaConsola(){
    	return jobs.get("CochesRESTaConsola").start(step4()).build();
    }
 
    @Bean
    public Step step4() {
    	return steps.get("ddd")
    			.<CocheDTO, CocheDTO> chunk(5)
    			.reader(readerRESTCoches())
    			.processor(processorMayusculas())
    			.writer(writerConsolaCoches())
    			.build();
    }

}

