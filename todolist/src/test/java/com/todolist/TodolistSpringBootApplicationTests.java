package com.todolist;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.configuration.SecurityConfiguration;
import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;
import com.todolist.repository.UserRepository;
import com.todolist.security.AuthenticationFilter;
import com.todolist.view.DateView;
import com.todolist.view.LoginDetailsView;
import com.todolist.view.TaskDetailsView;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TodolistSpringBootApplication.class)
@EnableWebMvc
@AutoConfigureMockMvc
public class TodolistSpringBootApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	ObjectMapper om = new ObjectMapper();
	
	 @MockBean
	 private TaskRepository mockedTaskRepository;
	 
	 @Autowired
	private UserRepository mockedUserRepository;
	
	 //testing Task Addition functionality
	@Test
	public void testSaveTask() throws Exception {
		
		//Setting the user details for JWT authentication 
		LoginDetailsView loginDetailsView=new LoginDetailsView();
		loginDetailsView.setName("test1");
		loginDetailsView.setPassword("pwd1");
		
		//converting the object into string
		String jsonRequestBody = om.writeValueAsString(loginDetailsView);
		
		//mocking the JWT api
		MvcResult result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/login").content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		
		//token recieved
		String token = result.getResponse().getHeader("token");
		
		
		
		
		//Mocking repository, so that databse won't be affected - purely for testing purpose
		Task task=new Task();
		Mockito.when(mockedTaskRepository.save(Mockito.any(Task.class))).thenReturn(task);
		
		//setting up task object which needs to be persisted
		TaskDetailsView taskDetailsView=new TaskDetailsView();
		taskDetailsView.setTaskDescription("Unit Testing");
		taskDetailsView.setTaskCheck(false);
		DateView dateView=new DateView();
		dateView.setDay(23);
		dateView.setMonth(11);
		dateView.setYear(2020);
		taskDetailsView.setDate(dateView);
		
		 jsonRequestBody = om.writeValueAsString(taskDetailsView);
		
		 //mocking add api
		 result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/api/task/add").header("Authorization", "Bearer "+token).content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		 
		
		String resultResponse = result.getResponse().getContentAsString();
		
		//checking the status of response
		assertEquals(result.getResponse().getStatus(), 200);
		
	}
	
	//testing Task Deletion Functionality
	@Test
	public void testDeleteTask() throws Exception{
		
		//Setting the user details for JWT authentication 
		LoginDetailsView loginDetailsView=new LoginDetailsView();
		loginDetailsView.setName("test1");
		loginDetailsView.setPassword("pwd1");
		
		
		String jsonRequestBody = om.writeValueAsString(loginDetailsView);
		
		//mocking the JWT api
		MvcResult result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/login").content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		
		//token recieved
		String token = result.getResponse().getHeader("token");
		
		//setting up task object which needs to be deleted
		TaskDetailsView taskDetailsView=new TaskDetailsView();
		taskDetailsView.setTaskId(321);
				
		
		
		result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/api/task/delete").header("Authorization", "Bearer "+token).content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		
		assertEquals(result.getResponse().getStatus(), 200);
	}
	
	//testing Task updation functionality
	@Test
	public void testUpdateTask() throws Exception{
			
		//Setting the user details for JWT authentication 
		LoginDetailsView loginDetailsView=new LoginDetailsView();
		loginDetailsView.setName("test1");
		loginDetailsView.setPassword("pwd1");
		
		
		String jsonRequestBody = om.writeValueAsString(loginDetailsView);
		
		//mocking the JWT api
		MvcResult result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/login").content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		
		//token recieved
		String token = result.getResponse().getHeader("token");
		
		//setting up task object which needs to be upated
		TaskDetailsView taskDetailsView=new TaskDetailsView();
		taskDetailsView.setTaskId(353);
		taskDetailsView.setTaskDescription("I am updating the task");
		
		Task task=new Task();
		Mockito.when(mockedTaskRepository.findTaskById(Mockito.any(Integer.class))).thenReturn(task);
		Mockito.when(mockedTaskRepository.save(Mockito.any(Task.class))).thenReturn(task);
		result = mockMvc.perform(
				   MockMvcRequestBuilders.post("/api/task/update").header("Authorization", "Bearer "+token).content(jsonRequestBody)
				   .contentType(MediaType.APPLICATION_JSON_VALUE))
			       .andExpect(MockMvcResultMatchers.status().isOk())
			       .andReturn();
		
		assertEquals(result.getResponse().getStatus(), 200);
	}

}
