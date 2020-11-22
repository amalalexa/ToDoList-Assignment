import { TaskService } from './../../service/task.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TaskDetails } from './../../view/TaskDetails';
import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  updatedTask:TaskDetails;
  description:String;
  @Output() update = new EventEmitter<TaskDetails[]>();
  
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private taskService:TaskService) { 
  }

  ngOnInit(): void {
    this.description=this.data.taskDetails.taskDescription;
  }

  form = new FormGroup({
    'taskDescription':new FormControl('',[
      Validators.required])
  });
  get taskDescription(){
    return this.form.get('taskDescription');
  }

  updateTaskDetails(form:FormGroup){
    this.updatedTask=form.value;
    this.updatedTask.taskId=this.data.taskDetails.taskId;
    this.updatedTask.taskCheck=this.data.taskDetails.taskCheck;
    this.taskService.updateTask(this.updatedTask).subscribe(res =>{
      this.update.emit(res);
    });
  }

}
