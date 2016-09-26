package com.curso.batch_4_CUSTOM;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.curso.dto.CocheDTO;
 
@Component
class ReaderRESTCoches implements ItemReader<CocheDTO> {
  
    private String apiUrl;
    
    @Autowired
    private RestTemplate restTemplate;
 
    //Estado
    private int pos;
    private List<CocheDTO> datos;
 
    public ReaderRESTCoches(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public CocheDTO read() throws Exception {
        if (datos == null) {
            datos = listarCoches();
        }
 
        CocheDTO coche = null;
 
        if (pos < datos.size()) {
            coche = datos.get(pos);
            pos++;
        }
 
        return coche;
    }
 
    private List<CocheDTO> listarCoches() {
        ResponseEntity<CocheDTO[]> response = restTemplate.getForEntity(
            apiUrl, 
            CocheDTO[].class
        );
        CocheDTO[] studentData = response.getBody();
        return Arrays.asList(studentData);
    }

}