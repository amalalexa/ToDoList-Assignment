package com.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todolist.model.Task;
import com.todolist.view.TaskDetailsView;

@Service
public class SwitchTaskAction {
	
	private CommandTask commandTask;
	
	public void setCommand(CommandTask commandTask) {
		
		this.commandTask=commandTask;
	}
	
	public List<Task> switchAction(TaskDetailsView taskDetailsView) throws Exception{
		
		return this.commandTask.execute(taskDetailsView);
	}

}
