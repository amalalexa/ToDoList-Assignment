package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.service.Action;
import com.todolist.service.TaskService;
import com.todolist.view.TaskDetailsView;



@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private Action action;
	
	@PostMapping("/add")
	public ResponseEntity<?> addTask(@RequestBody TaskDetailsView taskDetailsView){
		
		try {
			
			return ResponseEntity.ok().body(this.taskService.addTask(taskDetailsView));
			
		}catch(Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}

	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> deleteTask(@RequestBody TaskDetailsView taskDetailsView){
		
		try {
			
			return ResponseEntity.ok().body(taskService.deleteTask(taskDetailsView));
			
		}catch(Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateTask(@RequestBody TaskDetailsView taskDetailsView){
		
		try {
			
			return ResponseEntity.ok().body(this.taskService.updateTask(taskDetailsView));
			
		}catch(Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}

	}
	
	@GetMapping("/allTask")
	public ResponseEntity<?> getAllTask(){
		
		try {
			return ResponseEntity.ok().body(this.action.displayAllTask());
			
		}catch(Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}

	}
}
