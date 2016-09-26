package com.curso.batch_5_Parametros;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.JobContext;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.curso.batch_1_CSV.ProcessorMayusculas;
import com.curso.dto.CocheDTO;
 
@Configuration
public class JobConfigCSVaCVSParametros {
 
    @Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory steps;
    
    @Bean(name="readerCSVCochesParametros")
    @StepScope
    @Scope(value="step", proxyMode=ScopedProxyMode.TARGET_CLASS)
    @Value("#{jobParameters['ficheroEntrada']}")
    public FlatFileItemReader<CocheDTO> readerCSVCoches(String ficheroEntrada) {
        FlatFileItemReader<CocheDTO> reader = new FlatFileItemReader<CocheDTO>();
        reader.setResource(new ClassPathResource(ficheroEntrada));
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
        
    @Bean(name="writerCSVCochesParametros")
    @StepScope
    @Scope(value="step", proxyMode=ScopedProxyMode.TARGET_CLASS)
    @Value("#{jobParameters['ficheroSalida']}")
    public FlatFileItemWriter<CocheDTO> writerCSVCoches(String ficheroSalida) {
    	    	
    	FlatFileItemWriter<CocheDTO> writer = new FlatFileItemWriter<CocheDTO>();
    	
       	writer.setResource(new FileSystemResource(ficheroSalida));
		DelimitedLineAggregator<CocheDTO> delLineAgg = new DelimitedLineAggregator<CocheDTO>();
		delLineAgg.setDelimiter(",");
		BeanWrapperFieldExtractor<CocheDTO> fieldExtractor = new BeanWrapperFieldExtractor<CocheDTO>();
		fieldExtractor.setNames(new String[] {"id", "marca", "modelo", "matricula"});
		delLineAgg.setFieldExtractor(fieldExtractor);
		writer.setLineAggregator(delLineAgg);
		
		return writer;
    }        
 
    @Bean
    public Job CochesCSVaCSVParametros(@Qualifier("step5") Step step){
    	return jobs.get("CochesCSVaCSVParametros").start(step).build();
    }
 
    @Bean(name="step5")
    public Step step5(@Qualifier("readerCSVCochesParametros") ItemReader reader,
    		          @Qualifier("writerCSVCochesParametros") ItemWriter writer) {
    	return steps.get("step5")
    			.<CocheDTO, CocheDTO> chunk(5)
    			.reader(reader)
    			.processor(processorMayusculas())
    			.writer(writer)
    			.build();
    }

}

