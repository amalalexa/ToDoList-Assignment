import { TaskDetails } from './../view/TaskDetails';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http:HttpClient) { }

  addTask(task:TaskDetails){

    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('token') });
    let options = { headers: headers };

    return this.http.post<TaskDetails[]>(environment.apiUrl+"/api/task/add",task, options);
  }

  getAllTask(){

    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('token') });
    let options = { headers: headers };

    return this.http.get<TaskDetails[]>(environment.apiUrl+"/api/task/allTask",options);

  }

  updateTask(updateTask:TaskDetails){
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('token') });
    let options = { headers: headers };

    return this.http.post<TaskDetails[]>(environment.apiUrl+"/api/task/update",updateTask,options);
  }

  deleteTask(taskId:number){
    
    let task:TaskDetails;
    task=new TaskDetails();
    task.taskId=taskId;
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('token') });
    let options = { headers: headers };

    return this.http.post<TaskDetails[]>(environment.apiUrl+"/api/task/delete", task,options);

  }
}
