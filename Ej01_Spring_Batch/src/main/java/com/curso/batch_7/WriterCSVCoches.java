package com.curso.batch_7;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.curso.dto.CocheDTO;

//@Component
public class WriterCSVCoches extends FlatFileItemWriter<CocheDTO>{

	public WriterCSVCoches(){		
		this.setResource(new ClassPathResource("salida.csv"));
		DelimitedLineAggregator<CocheDTO> delLineAgg = new DelimitedLineAggregator<CocheDTO>();
		delLineAgg.setDelimiter(",");
		BeanWrapperFieldExtractor<CocheDTO> fieldExtractor = new BeanWrapperFieldExtractor<CocheDTO>();
		fieldExtractor.setNames(new String[] {"id", "marca", "modelo", "matricula"});
		delLineAgg.setFieldExtractor(fieldExtractor);
		this.setLineAggregator(delLineAgg);
	}
    
}
