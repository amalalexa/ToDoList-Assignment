package com.todolist.service;

import org.springframework.stereotype.Service;

import com.todolist.view.TaskDetailsView;

@Service
public class SwitchTaskAction {
	
	private CommandTask commandTask;
	
	public void setCommand(CommandTask commandTask) {
		
		this.commandTask=commandTask;
	}
	
	public String switchAction(TaskDetailsView taskDetailsView) throws Exception{
		
		return this.commandTask.execute(taskDetailsView);
	}

}
