package com.todolist.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
    
    @Autowired
    private TaskService taskService;

	@SuppressWarnings("deprecation")
	public List<Task> add(TaskDetailsView taskDetailsView) throws Exception {
		
		List<Task> listOfTask=new ArrayList<Task>();
		try {
			
			//creating a newTaskObject
			Task newTaskObject=new Task();
			
			//setting check=false initially
			newTaskObject.setTaskCheck(false);
			
			//setting task description
			newTaskObject.setTaskDescription(taskDetailsView.getTaskDescription());
			
			//set due date
			StringBuilder sb=new StringBuilder();
			sb.append(taskDetailsView.getDate().getDay()).append("/").append(taskDetailsView.getDate().getMonth()).append("/").append(taskDetailsView.getDate().getYear());
			Date dueDate=new SimpleDateFormat("dd/MM/yyyy").parse(sb.toString());
			newTaskObject.setDueDate(dueDate);
			
			//setting date to today's date
			Date today=new Date();
			newTaskObject.setLastUpdateDate(today);
			
			int userId=userIdRetrievalService.Getloggedinuserdetails().getUserId();
			//getting userID from token(JWT) 
			newTaskObject.setUserId(userId);
			//if(taskDetailsView.getDueDate().compareTo(today)>=0)
			//	throw new DateException("Due Date can't be less than Today's Date !!");
			
			//saving the newTaskObject to database
			taskRepository.save(newTaskObject);		
			
			
			
			return taskService.getAllTask();
			
			
		}catch(Exception e) {
			throw e;
		}
	
	}
	
	public List<Task> delete(TaskDetailsView taskDetailsView) throws Exception {
		
		try {
			
			Task taskObject=taskRepository.findTaskById(taskDetailsView.getTaskId());
			
			
			taskRepository.delete(taskObject);
			
			return taskService.getAllTask();
			
		}catch(Exception e) {
			throw e;
		}
	}
	
	public List<Task> update(TaskDetailsView taskDetailsView) throws Exception {
		
		try {
			
			Task taskObject=taskRepository.findTaskById(taskDetailsView.getTaskId());
			
			taskObject.setTaskDescription(taskDetailsView.getTaskDescription());
			BeanUtils.copyProperties(taskDetailsView, taskObject);
			Date now=new Date();
			taskObject.setLastUpdateDate(now);
			
			taskRepository.save(taskObject);
			
			return taskService.getAllTask();
			
		}catch(Exception e) {
			throw e;
		}
	}
}
