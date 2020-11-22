package com.todolist.view;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDetailsView {

	private String taskDescription;
	private int taskId;
	private boolean taskCheck;
	//private Date dueDate;
	
}
