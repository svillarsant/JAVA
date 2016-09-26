package com.curso.batch_7;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.curso.dto.CocheDTO;

//@Configuration
public class ReaderBBDDCoches extends JdbcCursorItemReader<CocheDTO> 
							  implements InitializingBean {
 
    private static final String QUERY = "SELECT * FROM coches";
 
    @Autowired
    private DataSource dataSource;

    @Override
    public void afterPropertiesSet() throws Exception{
    	super.afterPropertiesSet();
    	this.setDataSource(dataSource);
    	this.setSql(QUERY);
    	this.setRowMapper(new BeanPropertyRowMapper<CocheDTO>(CocheDTO.class));
    }
    
}




