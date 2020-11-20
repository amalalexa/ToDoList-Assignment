package com.todolist.model;

import java.util.Date;

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
@Table(name="task")
@Getter
@Setter
public class Task {

	@Id
	@GeneratedValue(generator = "taskId-generator")
	@GenericGenerator(name = "taskId-generator", 
					  parameters = @Parameter(name = "prefix", value = "T"), 
					  strategy = "com.todolist.model.GenerateId")
	@Column(name="task_id")
	private String taskId;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="last_update_date")
	private Date lastUpdateDate;
	
	//@Column(name="due_date")
	//private Date dueDate;
	
	@Column(name="task_description")
	private String taskDescription;
	
	@Column(name="task_check")
	private Boolean taskCheck;
	
}
