package com.curso.batch_7;

import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import com.curso.dto.CocheDTO;

//@Component
public class ReaderXMLCoches extends StaxEventItemReader<CocheDTO>{
 
	public ReaderXMLCoches(){
		this.setResource(new ClassPathResource("data/coches.xml"));
		this.setFragmentRootElementName("coche");
		
		Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
		unmarshaller.setClassesToBeBound(CocheDTO.class);
		this.setUnmarshaller(unmarshaller);
	}
	
}