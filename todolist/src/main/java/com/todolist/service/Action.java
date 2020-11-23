package com.todolist.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;
import com.todolist.view.TaskDetailsView;

@Service
public class Action {
	
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserIdRetrievalService userIdRetrievalService;
    

	public void add(TaskDetailsView taskDetailsView) throws Exception {
		

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
			
			return;
			
			
		}catch(Exception e) {
			throw e;
		}
	
	}
	
	public void delete(TaskDetailsView taskDetailsView) throws Exception {
		
		try {
			
			//retrieving object from db using taskId
			Task taskObject=taskRepository.findTaskById(taskDetailsView.getTaskId());
			
			//delete the corresponding entity
			taskRepository.delete(taskObject);
			
			//return all Tasks
			return;
			
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void update(TaskDetailsView taskDetailsView) throws Exception {
		
		try {
			
			//get the corresponding task object based on taskId
			Task taskObject=taskRepository.findTaskById(taskDetailsView.getTaskId());
			
			//modify the details and set to the last modification date(i.e, Date now)
			taskObject.setTaskDescription(taskDetailsView.getTaskDescription());
			BeanUtils.copyProperties(taskDetailsView, taskObject);
			Date now=new Date();
			taskObject.setLastUpdateDate(now);
			
			//save the object back to database
			taskRepository.save(taskObject);

			return;
			
		}catch(Exception e) {
			throw e;
		}
	}
	
	public List<Task> displayAllTask() throws Exception{
		try {
			
			int userId=userIdRetrievalService.Getloggedinuserdetails().getUserId();
			
			return taskRepository.findAllTaskByUserId(userId);
		}catch(Exception e) {
			throw e;
		}
	}
}
