package com.todolist.service;

import java.util.List;

import com.todolist.model.Task;
import com.todolist.view.TaskDetailsView;

public class TaskAddService implements CommandTask{

	Action action;
	
	public TaskAddService(Action action) {
		this.action=action;
	}
	public List<Task> execute(TaskDetailsView taskDetailsView) throws Exception {
		
		try {
			return this.action.add(taskDetailsView);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
