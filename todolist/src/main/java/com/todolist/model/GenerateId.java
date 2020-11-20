package com.todolist.model;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.todolist.repository.ParentRepository;
import com.todolist.util.BeanUtil;


public class GenerateId implements IdentifierGenerator,Configurable{

	private String prefix;
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) {
		
		int max=0;
		Stream ids;
		
		try {
			
				Class repositoryChildInterface = Class.forName("com.todolist.repository."+obj.getClass().getSimpleName()+"Repository");
				ParentRepository repositoryParentInterface= (ParentRepository) BeanUtil.getBean(repositoryChildInterface);
				List<String> listOfId = repositoryParentInterface.findAllIds();
		

				ids = listOfId.stream();

				max = ids.map(value -> value.toString().replace(prefix+"-", ""))
						 .mapToInt(value -> Integer.parseInt(value.toString()))
						 .max()
						 .orElse(0);
				System.out.println(max);
			
			}catch(Exception e) {
				
				System.out.println(e.getMessage());
			}
		
			return prefix + "-" + (max+1);
	}
	
	@Override
	public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry){
	    prefix = properties.getProperty("prefix");
	}
}

