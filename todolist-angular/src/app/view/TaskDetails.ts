import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

export class TaskDetails{
    taskId:number=0;
    userId:number;
    taskDescription:String;
    lastUpdateDate:Date;
    taskCheck:boolean;
    date:NgbDateStruct;
    dueDate:Date;
}