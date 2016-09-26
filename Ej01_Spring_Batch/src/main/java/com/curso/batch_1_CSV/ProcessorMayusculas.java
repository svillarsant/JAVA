package com.curso.batch_1_CSV;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.curso.dto.CocheDTO;

@Component
public class ProcessorMayusculas implements ItemProcessor<CocheDTO, CocheDTO> {

	@Override
	public CocheDTO process(final CocheDTO coche) throws Exception {
		coche.setMarca(coche.getMarca().toUpperCase());
		coche.setModelo(coche.getModelo().toUpperCase());
		coche.setMatricula(coche.getMatricula().toUpperCase());
		return coche;
	}

}
