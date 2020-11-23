package com.todolist.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="task_id")
	private int taskId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="last_update_date")
	private Date lastUpdateDate;
	
	@Column(name="due_date")
	private Date dueDate;
	
	@Column(name="task_description")
	private String taskDescription;
	
	@Column(name="task_check")
	private Boolean taskCheck;
	
}
