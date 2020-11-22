package com.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todolist.model.Task;
import com.todolist.view.TaskDetailsView;

@Service
public class TaskDeleteService implements CommandTask{
	
	Action action;
	
	public TaskDeleteService(Action action) {
		this.action=action;
	}
	public List<Task> execute(TaskDetailsView taskDetailsView) throws Exception {
		
		try {
			return this.action.delete(taskDetailsView);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
