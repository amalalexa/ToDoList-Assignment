package com.todolist.service;

import org.springframework.stereotype.Service;

import com.todolist.view.TaskDetailsView;

@Service
public class TaskDeleteService implements CommandTask{
	
	Action action;
	
	public TaskDeleteService(Action action) {
		this.action=action;
	}
	public String execute(TaskDetailsView taskDetailsView) throws Exception {
		
		try {
			return this.action.delete(taskDetailsView);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
