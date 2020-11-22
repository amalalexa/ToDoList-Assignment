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


  listOfTaskDetails$:TaskDetails[];
  task:TaskDetails;
  vehicles: Observable<Array<TaskDetails>>
  constructor(private dialog:MatDialog, private taskService:TaskService) { }

  ngOnInit(): void {

    this.taskService.getAllTask().subscribe((res)=>{
        this.listOfTaskDetails$=res;
    });
  }

  addTask(){
    let dialogRef=this.dialog.open(AddComponent);

    const subscribeDialog= dialogRef.componentInstance.change.subscribe((data)=>{
      this.listOfTaskDetails$=data;
      subscribeDialog.unsubscribe();
    });

    dialogRef.afterClosed().subscribe(result =>{
      console.log("Form closed");
    });
  }

  updateTask(taskId:number){
    
    for(let i=0;i<this.listOfTaskDetails$.length;i++)
    {
      if(this.listOfTaskDetails$[i].taskId==taskId)
      {
        this.task=this.listOfTaskDetails$[i];
        break;
      }
    }

    let dialogRef=this.dialog.open(UpdateComponent,{
      data: {
        taskDetails: this.task
      }
    });

    let subscribeDialog= dialogRef.componentInstance.update.subscribe((data)=>{
      this.listOfTaskDetails$=data;
      subscribeDialog.unsubscribe();
    });

    dialogRef.afterClosed().subscribe(result =>{
      console.log("Form closed");
    });

  }

  deleteTask(taskId:number){
    this.taskService.deleteTask(taskId).subscribe(res =>{
      this.listOfTaskDetails$=res;
    });
  }

}
