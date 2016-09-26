package com.curso.batch_2_XML;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.curso.dto.CocheDTO;

@Component
public class ProcessorValidacion implements ItemProcessor<CocheDTO, CocheDTO> {

	@Override
	public CocheDTO process(CocheDTO coche) throws Exception {
		System.out.println("=========================================================");
		
		if(coche.getMarca().trim().length() == 0){
			return null;
		}
		
		return coche;
	}

}
