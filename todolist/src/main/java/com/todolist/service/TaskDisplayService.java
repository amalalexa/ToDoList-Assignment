package com.todolist.service;

import java.util.List;

import com.todolist.model.Task;

public class TaskDisplayService implements CommandTask{
	
	Action action;
	
	public TaskDisplayService(Action action) {
		this.action=action;
	}
	//overriding execute method to execute the recieved action, in here 'display all task'
	public List<Task> execute() throws Exception {
		
		try {
			//executing the display task logic within 'Action' class
			return this.action.displayAllTask();
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}	


}
