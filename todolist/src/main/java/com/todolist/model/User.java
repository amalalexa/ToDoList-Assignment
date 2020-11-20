package com.todolist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(generator = "userId-generator")
	@GenericGenerator(name = "userId-generator", 
					  parameters = @Parameter(name = "prefix", value = "U"), 
					  strategy = "com.todolist.model.GenerateId")
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;

}
