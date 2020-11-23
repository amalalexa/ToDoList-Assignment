package com.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todolist.model.Task;
import com.todolist.view.TaskDetailsView;

//implements Command Interface

public class TaskDeleteService implements CommandTask{
	
	Action action;
	TaskDetailsView taskDetailsView;
	
	public TaskDeleteService(Action action,TaskDetailsView taskDetailsView) {
		this.action=action;
		this.taskDetailsView=taskDetailsView;
	}
	//overriding execute method to execute the recieved action, in here 'deletion of task'
	public List<Task> execute() throws Exception {
		
		try {
			//executing the delete logic within 'Action' class
			this.action.delete(this.taskDetailsView);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}

}
