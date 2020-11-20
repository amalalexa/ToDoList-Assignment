package com.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.view.TaskDetailsView;

@Service
public class TaskService {
	
	@Autowired
	private SwitchTaskAction switchTaskAction;
	
	@Autowired
	private Action action;
	
	public String addTask(TaskDetailsView taskDetailsView) throws Exception {
		
		CommandTask commandTask=new TaskAddService(action);
		switchTaskAction.setCommand(commandTask);
		return switchTaskAction.switchAction(taskDetailsView);
		
	}
	public String deleteTask(TaskDetailsView taskDetailsView) throws Exception {
		
		CommandTask commandTask=new TaskDeleteService(action);
		switchTaskAction.setCommand(commandTask);
		return switchTaskAction.switchAction(taskDetailsView);
	}
	public String updateTask(TaskDetailsView taskDetailsView) throws Exception {
		
		CommandTask commandTask=new TaskUpdateService(action);
		switchTaskAction.setCommand(commandTask);
		return switchTaskAction.switchAction(taskDetailsView);
		
	}
	

}
