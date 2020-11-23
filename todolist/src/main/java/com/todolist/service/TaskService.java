package com.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.model.Task;
import com.todolist.view.TaskDetailsView;

@Service
public class TaskService {
	
	@Autowired
	private SwitchTaskAction switchTaskAction;
	
	@Autowired
	private Action action;
	

	
	public List<Task> addTask(TaskDetailsView taskDetailsView) throws Exception {
		
		//initializing object for Add command
		TaskAddService taskAddService=new TaskAddService(action,taskDetailsView);
		//initializing object for display command
		TaskDisplayService taskDisplayService=new TaskDisplayService(action);
		
		//add the two commands to SwitchTaskAction, which handles the execution of the logic
		switchTaskAction.addCommand(taskAddService);
		switchTaskAction.addCommand(taskDisplayService);
		
		//returning the list of tasks
		return switchTaskAction.executeCommands();
		
	}
	public List<Task> deleteTask(TaskDetailsView taskDetailsView) throws Exception {
		
		//initializing object for delete command
		TaskDeleteService taskDeleteService=new TaskDeleteService(action,taskDetailsView);
		//initializing object for display command
		TaskDisplayService taskDisplayService=new TaskDisplayService(action);
		
		//add the two commands to SwitchTaskAction, which handles the execution of the logic
		switchTaskAction.addCommand(taskDeleteService);
		switchTaskAction.addCommand(taskDisplayService);
		
		//returning the list of tasks
		return switchTaskAction.executeCommands();
	}
	public List<Task> updateTask(TaskDetailsView taskDetailsView) throws Exception {
		
		//initializing object for update command
		TaskUpdateService taskUpdateService=new TaskUpdateService(action,taskDetailsView);
		//initializing object for display command
		TaskDisplayService taskDisplayService=new TaskDisplayService(action);
		
		//add the two commands to SwitchTaskAction, which handles the execution of the logic
		switchTaskAction.addCommand(taskUpdateService);
		switchTaskAction.addCommand(taskDisplayService);
		
		//returning the list of tasks
		return switchTaskAction.executeCommands();
		
	}

}
