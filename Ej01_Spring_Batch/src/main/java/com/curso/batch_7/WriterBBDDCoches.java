package com.curso.batch_7;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.curso.dto.CocheDTO;

//@Component
public class WriterBBDDCoches extends JdbcBatchItemWriter<CocheDTO>
							  implements InitializingBean{

	@Autowired
	private DataSource dataSource;
	
	@Override
	public void afterPropertiesSet(){
		super.afterPropertiesSet();
		this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CocheDTO>());
        this.setSql("INSERT INTO coches (marca,modelo,matricula) VALUES (:marca,:modelo,:matricula)");
        this.setDataSource(dataSource);
	}
    
}
