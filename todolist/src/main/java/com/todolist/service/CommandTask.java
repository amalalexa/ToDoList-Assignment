package com.todolist.service;

import java.util.List;

import com.todolist.model.Task;

//command pattern
public interface CommandTask {
	
	public List<Task> execute() throws Exception;

}
