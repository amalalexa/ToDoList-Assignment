package com.todolist.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;
import com.todolist.repository.UserRepository;
import com.todolist.view.TaskDetailsView;

@Service
public class Action {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserIdRetrievalService userIdRetrievalService;

	@SuppressWarnings("deprecation")
	public String add(TaskDetailsView taskDetailsView) {
		
		try {
			
			//creating a newTaskObject
			Task newTaskObject=new Task();
			
			//setting check=false initially
			newTaskObject.setTaskCheck(false);
			
			//setting task description
			newTaskObject.setTaskDescription(taskDetailsView.getTaskDescription());
			
			
			//setting date to today's date
			Date today=new Date();
			newTaskObject.setLastUpdateDate(today);
			
			//getting userID from token(JWT) userIdRetrievalService.Getloggedinuserdetails().getUserId()
			newTaskObject.setUserId(0);
			//if(taskDetailsView.getDueDate().compareTo(today)>=0)
			//	throw new DateException("Due Date can't be less than Today's Date !!");
			
			//saving the newTaskObject to database
			taskRepository.save(newTaskObject);			
			return "Task Added !!!";
			
			
		}catch(Exception e) {
			throw e;
		}
	
	}
	
	public String delete(TaskDetailsView taskDetailsView) throws Exception {
		
		try {
			
			Task taskObject=taskRepository.findTaskById(taskDetailsView.getTaskId());
			
			taskRepository.delete(taskObject);
			
			return "Task Deleted !!!";
			
		}catch(Exception e) {
			throw e;
		}
	}
	
	public String update(TaskDetailsView taskDetailsView) throws Exception {
		
		try {
			
			Task taskObject=taskRepository.findTaskById(taskDetailsView.getTaskId());
			
			taskObject.setTaskDescription(taskDetailsView.getTaskDescription());
			
			Date now=new Date();
			taskObject.setLastUpdateDate(now);
			
			taskRepository.save(taskObject);
			
			return "Task Updated !!!";
			
		}catch(Exception e) {
			throw e;
		}
	}
}
