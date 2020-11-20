package com.todolist.service;

import com.todolist.view.TaskDetailsView;

public class TaskAddService implements CommandTask{

	Action action;
	
	public TaskAddService(Action action) {
		this.action=action;
	}
	public String execute(TaskDetailsView taskDetailsView) throws Exception {
		
		try {
			return this.action.add(taskDetailsView);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
