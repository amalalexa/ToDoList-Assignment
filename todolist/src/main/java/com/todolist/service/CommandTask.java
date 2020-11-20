package com.todolist.service;

import com.todolist.view.TaskDetailsView;

public interface CommandTask {
	
	public String execute(TaskDetailsView taskDetailsView) throws Exception;

}
