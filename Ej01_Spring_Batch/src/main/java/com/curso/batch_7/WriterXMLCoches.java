package com.curso.batch_7;

import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import com.curso.dto.CocheDTO;

//@Component
public class WriterXMLCoches extends StaxEventItemWriter<CocheDTO>{

	public WriterXMLCoches(){
		this.setResource(new FileSystemResource("e:/salida.xml"));
		this.setRootTagName("coche");
		
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(CocheDTO.class);
		
		this.setMarshaller(marshaller);			
	}
    
}

