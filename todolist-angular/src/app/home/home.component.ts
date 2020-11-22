import { UpdateComponent } from './../task/update/update.component';
import { TaskService } from './../service/task.service';
import { TaskDetails } from './../view/TaskDetails';
import { AddComponent } from './../task/add/add.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  listOfTask:boolean=false;
  listOfTaskDetails$:TaskDetails[];
  task:TaskDetails;
  vehicles: Observable<Array<TaskDetails>>
  constructor(private dialog:MatDialog, private taskService:TaskService) { }

  ngOnInit(): void {

    this.taskService.getAllTask().subscribe((res)=>{
        this.listOfTaskDetails$=res;
        if(this.listOfTaskDetails$.length==0)
          this.listOfTask=true;
    });
  }

  addTask(){
    let dialogRef=this.dialog.open(AddComponent);

    const subscribeDialog= dialogRef.componentInstance.change.subscribe((data)=>{
      this.listOfTaskDetails$=data;
      if(this.listOfTaskDetails$.length==0)
          this.listOfTask=true;
      else  
          this.listOfTask=false;
      subscribeDialog.unsubscribe();
    });

    dialogRef.afterClosed().subscribe(result =>{
      console.log("Form closed");
    });
  }

  updateTask(task:TaskDetails){
    console.log(task);
    let dialogRef=this.dialog.open(UpdateComponent,{
      data: {
        taskDetails:task
      }
    });
    
    let subscribeDialog= dialogRef.componentInstance.update.subscribe((data)=>{
      this.listOfTaskDetails$=data;
      if(this.listOfTaskDetails$.length==0)
          this.listOfTask=true;
      else
          this.listOfTask=false;
      subscribeDialog.unsubscribe();
    });

    dialogRef.afterClosed().subscribe(result =>{
      console.log("Form closed");
    });

  }

  deleteTask(task:TaskDetails){
    this.taskService.deleteTask(task.taskId).subscribe(res =>{
      this.listOfTaskDetails$=res;
      if(this.listOfTaskDetails$.length==0)
          this.listOfTask=true;
      else
          this.listOfTask=false;
    });
  }

  checkTask($event,task:TaskDetails){

      task.taskCheck=$event.checked;
      console.log(task);
      this.taskService.updateTask(task).subscribe(res =>{
        this.listOfTaskDetails$=res;
      });
    
      
  }

}
