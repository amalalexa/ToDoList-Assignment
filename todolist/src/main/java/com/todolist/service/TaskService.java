package com.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;
import com.todolist.view.TaskDetailsView;

@Service
public class TaskService {
	
	@Autowired
	private SwitchTaskAction switchTaskAction;
	
	@Autowired
	private Action action;
	
	@Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserIdRetrievalService userIdRetrievalService;

	
	public List<Task> addTask(TaskDetailsView taskDetailsView) throws Exception {
		
		CommandTask commandTask=new TaskAddService(action);
		switchTaskAction.setCommand(commandTask);
		return switchTaskAction.switchAction(taskDetailsView);
		
	}
	public List<Task> deleteTask(TaskDetailsView taskDetailsView) throws Exception {
		
		CommandTask commandTask=new TaskDeleteService(action);
		switchTaskAction.setCommand(commandTask);
		return switchTaskAction.switchAction(taskDetailsView);
	}
	public List<Task> updateTask(TaskDetailsView taskDetailsView) throws Exception {
		
		CommandTask commandTask=new TaskUpdateService(action);
		switchTaskAction.setCommand(commandTask);
		return switchTaskAction.switchAction(taskDetailsView);
		
	}
	
	public List<Task> getAllTask() throws Exception{
		try {
			
			int userId=userIdRetrievalService.Getloggedinuserdetails().getUserId();
			
			return taskRepository.findAllTaskByUserId(userId);
		}catch(Exception e) {
			throw e;
		}
	}

}
