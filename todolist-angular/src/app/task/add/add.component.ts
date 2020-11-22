import { TaskService } from './../../service/task.service';
import { TaskDetails } from './../../view/TaskDetails';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  task:TaskDetails;

  @Output() change = new EventEmitter<TaskDetails[]>();
  
  constructor(private taskService:TaskService) { }

  ngOnInit(): void {
  }
  form = new FormGroup({
    'taskDescription':new FormControl('',[
      Validators.required])
  });
  get taskDescription(){
    return this.form.get('taskDescription');
  }
  
  addTaskDetails(form:FormGroup){
    this.task=form.value;
    this.taskService.addTask(this.task).subscribe(
      response=>{
        this.change.emit(response);
      },
      error=>{
        console.log(error);
      }
    );

  }

}
