package com.todolist.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.exception.UserIdException;
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

	@SuppressWarnings("deprecation")
	public String add(TaskDetailsView taskDetailsView) {
		
		try {
			
			// to check if the User(userId) exsists or not
			if(userRepository.checkUserExsists(taskDetailsView.getUserId())==0)
				throw new UserIdException("User Doesn't Exsists !!!");
			
			//creating a newTaskObject
			Task newTaskObject=new Task();
			
			//copying data member values to Task object
			BeanUtils.copyProperties(taskDetailsView, newTaskObject);
			
			//setting values to newTaskObject
			newTaskObject.setTaskCheck(false);
			
			Date today=new Date();
			newTaskObject.setLastUpdateDate(today);
			
			//if(taskDetailsView.getDueDate().compareTo(today)>=0)
			//	throw new DateException("Due Date can't be less than Today's Date !!");
			
			//saving the newTaskObject to database
			taskRepository.save(newTaskObject);			
			return "Task Added !!!";
			
			
		}catch(UserIdException e) {
			return e.getMessage();
		}catch(Exception e) {
			return e.getMessage();
		}
	
	}
	
	public String delete(TaskDetailsView taskDetailsView) {
		
		try {
			
			if(taskDetailsView.getTaskId()==null)
				throw new Exception("Task is not deleted !!!");
			
			taskRepository.deleteById(taskDetailsView.getTaskId());
			
			return "Task Deleted !!!";
			
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	public String update(TaskDetailsView taskDetailsView) {
		
		try {
			
			if(taskDetailsView.getTaskId()==null)
				throw new Exception("Task is not deleted !!!");
			
			Task taskObject=taskRepository.findTaskById(taskDetailsView.getTaskId());
			
			taskObject.setTaskDescription(taskDetailsView.getTaskDescription());
			
			Date now=new Date();
			taskObject.setLastUpdateDate(now);
			
			taskRepository.save(taskObject);
			
			return "Task Updated !!!";
			
		}catch(Exception e) {
			return e.getMessage();
		}
	}
}
