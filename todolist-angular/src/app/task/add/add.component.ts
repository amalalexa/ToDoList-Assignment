import { TaskService } from './../../service/task.service';
import { TaskDetails } from './../../view/TaskDetails';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  task:TaskDetails;
  model:NgbDateStruct;
  minDate:any;
  @Output() change = new EventEmitter<TaskDetails[]>();
  
  constructor(private taskService:TaskService) {
    const current = new Date();
  this.minDate = {
    year: current.getFullYear(),
    month: current.getMonth() + 1,
    day: current.getDate()
  };
   }

  ngOnInit(): void {
  }
  form = new FormGroup({
    'taskDescription':new FormControl('',[
      Validators.required]),
    'date':new FormControl('',[
      Validators.required])
  });
  get taskDescription(){
    return this.form.get('taskDescription');
  }

  get date(){
    return this.form.get('date');
  }
  
  addTaskDetails(form:FormGroup){
    this.task=form.value;
    console.log(form.value);
    console.log(this.task);
    
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
