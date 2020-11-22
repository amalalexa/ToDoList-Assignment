package com.todolist.service;

import java.util.List;

import com.todolist.model.Task;
import com.todolist.view.TaskDetailsView;

public interface CommandTask {
	
	public List<Task> execute(TaskDetailsView taskDetailsView) throws Exception;

}
