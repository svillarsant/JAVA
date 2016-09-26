package com.curso.batch_4_CUSTOM;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.curso.dto.CocheDTO;

@Component
public class WriterConsolaCoches implements ItemWriter<CocheDTO>{

	@Override
	public void write(List<? extends CocheDTO> datos) throws Exception {

		for(CocheDTO coche: datos){
			System.out.println(coche);
		}		
		
	}	
	
}
