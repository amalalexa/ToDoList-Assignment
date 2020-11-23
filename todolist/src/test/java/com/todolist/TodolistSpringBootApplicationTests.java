/****************************
 * 
 * JUnit Testing
 * 
 * Testing functionalities Add task, Update task and Delete task.
 * Mockito used to mock the database
 * MockMvc used to call the design API's
 */
package com.todolist;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;
import com.todolist.repository.UserRepository;
import com.todolist.view.DateView;
import com.todolist.view.LoginDetailsView;
import com.todolist.view.TaskDetailsView;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TodolistSpringBootApplication.class)
@EnableWebMvc
@AutoConfigureMockMvc
public class TodolistSpringBootApplicationTests {
	
	//Creating a MockMvc Bean
	@Autowired
	private MockMvc mockMvc;
	
	//Mapping objects to string
	ObjectMapper om = new ObjectMapper();
	
	//Mocking the Task repository
	@MockBean
	private TaskRepository mockedTaskRepository;
	 
	
	//testing Task Addition functionality
	@Test
	public void testSaveTask() throws Exception {
		
		//Setting the user details for JWT authentication 
		LoginDetailsView loginDetailsView=new LoginDetailsView();
		loginDetailsView.setName("test1");
		loginDetailsView.setPassword("pwd1");
		
		//converting the object into string
		String jsonRequestBody = om.writeValueAsString(loginDetailsView);
		
		//calling the login api to verify the credentials
		MvcResult result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/login").content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		
		//token extracted from the response header
		String token = result.getResponse().getHeader("token");
		
		//Mocking repository so that databse won't be affected - purely for testing purpose
		
		Task task=new Task(); //return task object if save() works fine
		Mockito.when(mockedTaskRepository.save(Mockito.any(Task.class))).thenReturn(task);
		
		List<Task> arrayOfTask=new ArrayList<Task>(); //return arrayOfTask object if findAllTaskByUserId() works fine
		Mockito.when(mockedTaskRepository.findAllTaskByUserId(Mockito.any(Integer.class))).thenReturn(arrayOfTask);
		
		
		//Initializing the task object which needs to be persisted
		TaskDetailsView taskDetailsView=new TaskDetailsView();
		taskDetailsView.setTaskDescription("Mockito Testing"); //task description
		taskDetailsView.setTaskCheck(false); //task check
		
		DateView dateView=new DateView(); //due date for task
		dateView.setDay(23);
		dateView.setMonth(11);
		dateView.setYear(2020);
		taskDetailsView.setDate(dateView);
		
		//converting object to string
		jsonRequestBody = om.writeValueAsString(taskDetailsView);
		
		 //calling task add api
		 result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/api/task/add").header("Authorization", "Bearer "+token).content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		 
		//extracting reponse from the response body
		String resultResponse = result.getResponse().getContentAsString();
		
		//verifying the results
		assertEquals(resultResponse, arrayOfTask.toString());
		
	}
	
	//testing Task Deletion Functionality
	@Test
	public void testDeleteTask() throws Exception{
		
		//Setting the user details for JWT authentication 
		LoginDetailsView loginDetailsView=new LoginDetailsView();
		loginDetailsView.setName("test1");
		loginDetailsView.setPassword("pwd1");
		
		//converting object to string
		String jsonRequestBody = om.writeValueAsString(loginDetailsView);
		
		//calling the login api
		MvcResult result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/login").content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		
		//token extracted from response header
		String token = result.getResponse().getHeader("token");
		
		//initializing the task object which needs to be deleted
		TaskDetailsView taskDetailsView=new TaskDetailsView();
		taskDetailsView.setTaskId(321);
		
		//Mocking repository so that databse won't be affected - purely for testing purpose
		
		List<Task> arrayOfTask=new ArrayList<Task>(); //return arrayOfTask object if findAllTaskByUserId() works fine
		Mockito.when(mockedTaskRepository.findAllTaskByUserId(Mockito.any(Integer.class))).thenReturn(arrayOfTask);
		
		//calling delete api
		result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/api/task/delete").header("Authorization", "Bearer "+token).content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		
		//extracting reponse from the response body
		String resultResponse = result.getResponse().getContentAsString();
		
		//verifying the status
		assertEquals(resultResponse, arrayOfTask.toString());
	}
	
	//testing Task updation functionality
	@Test
	public void testUpdateTask() throws Exception{
			
		//Setting the user details for JWT authentication 
		LoginDetailsView loginDetailsView=new LoginDetailsView();
		loginDetailsView.setName("test1");
		loginDetailsView.setPassword("pwd1");
		
		//converting object to it's string representation
		String jsonRequestBody = om.writeValueAsString(loginDetailsView);
		
		//calling the login api
		MvcResult result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/login").content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		
		//token received
		String token = result.getResponse().getHeader("token");
		
		//initializing the task object which needs to be upated
		TaskDetailsView taskDetailsView=new TaskDetailsView();
		taskDetailsView.setTaskId(353);
		taskDetailsView.setTaskDescription("I am updating the task");
		
		//Mocking repository so that database won't be affected - purely for testing purpose
		
		Task task=new Task(); //return task when repositoryBean calls findTaskById() and save() 
		Mockito.when(mockedTaskRepository.findTaskById(Mockito.any(Integer.class))).thenReturn(task);
		Mockito.when(mockedTaskRepository.save(Mockito.any(Task.class))).thenReturn(task);
		
		List<Task> arrayOfTask=new ArrayList<Task>(); //return arrayOfTask object if findAllTaskByUserId() works fine
		Mockito.when(mockedTaskRepository.findAllTaskByUserId(Mockito.any(Integer.class))).thenReturn(arrayOfTask);
		
		//calling the update api
		result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/api/task/update").header("Authorization", "Bearer "+token).content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		
		//extract the body of response
		String resultResponse = result.getResponse().getContentAsString();
		
		//verify the response
		assertEquals(resultResponse, arrayOfTask.toString());
	}

}
