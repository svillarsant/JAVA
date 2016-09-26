package com.curso.batch_6;

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
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.curso.batch_1_CSV.ProcessorMayusculas;
import com.curso.dto.CocheDTO;
 
@Configuration
@EnableBatchProcessing
public class JobConfigCSVaCSVListener {
 
    @Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory steps;
    
    @Bean
    public ItemReader<CocheDTO> readerCSVCoches() {
        FlatFileItemReader<CocheDTO> reader = new FlatFileItemReader<CocheDTO>();
        reader.setResource(new ClassPathResource("datos/entrada.csv"));
        reader.setLineMapper(new DefaultLineMapper<CocheDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] {"id", "marca", "modelo", "matricula"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CocheDTO>() {{
                setTargetType(CocheDTO.class);
            }});
        }});
        return reader;
    }     
    
    @Bean
    public ItemProcessor<CocheDTO, CocheDTO> processorMayusculas() {
        return new ProcessorMayusculas();
    }    
        
    @Bean
    public ItemWriter<CocheDTO> writerCSVCoches() {
    	
    	FlatFileItemWriter<CocheDTO> writer = new FlatFileItemWriter<CocheDTO>();
    	
    	writer.setResource(new FileSystemResource("e:/salida.csv"));
		//writer.setResource(new ClassPathResource("datos/salida.csv"));

		DelimitedLineAggregator<CocheDTO> delLineAgg = new DelimitedLineAggregator<CocheDTO>();
		delLineAgg.setDelimiter(",");
		BeanWrapperFieldExtractor<CocheDTO> fieldExtractor = new BeanWrapperFieldExtractor<CocheDTO>();
		fieldExtractor.setNames(new String[] {"id", "marca", "modelo", "matricula"});
		delLineAgg.setFieldExtractor(fieldExtractor);
		writer.setLineAggregator(delLineAgg);
		
		return writer;
    }        
 
    @Bean
    public Job CochesCSVaCSVListener(){
    	return jobs.get("CochesCSVaCSVListener").
    			listener(new OyenteJob()).
    			start(step6()).
    			build();
    }
 
    @Bean
    public Step step6() {
    	return steps.get("step6")
    			.<CocheDTO, CocheDTO> chunk(5)
    			.reader(readerCSVCoches())
    			.processor(processorMayusculas())
    			.writer(writerCSVCoches())
    			.build();
    }

}

