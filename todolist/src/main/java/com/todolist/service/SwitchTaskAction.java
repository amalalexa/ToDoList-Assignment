package com.todolist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.todolist.model.Task;

//service which switch the task 
@Service
public class SwitchTaskAction {
	
	private List<CommandTask> listOfCommands=new ArrayList<CommandTask>();
	
	public void addCommand(CommandTask commandTask) {
		
		this.listOfCommands.add(commandTask);
	}
	
	//execute the commands in the listOfCommands
	public List<Task> executeCommands() throws Exception {
		
		for(CommandTask command:this.listOfCommands) {
			if(command instanceof TaskDisplayService)
				try {
					//Storing the last command
					CommandTask lastCommand=command;
					//clearing the list of commands, so previous list of operations are cleared off.
					this.listOfCommands.clear(); 
					return lastCommand.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			command.execute();
		}
		return new ArrayList<Task>();
	}
}
