import { UserService } from './../service/user.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserSignUpDetails } from '../view/UserSignUpDetails';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user:UserSignUpDetails;
  error:String='';
  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }

  form = new FormGroup({
    'name':new FormControl('',[
      Validators.required,
      Validators.pattern("[a-zA-Z]+")]), //simple validation for name
    'password':new FormControl('',[
      Validators.required,
      Validators.pattern("[a-zA-Z@_]*") //simple validation for pasword
    ])
  });
  get name(){
    return this.form.get('name');
  }
  get password(){
    return this.form.get('password');
  }
  loggingInUser(form:FormGroup){
    this.user=form.value;
    this.userService.login(this.user).subscribe(
      response=>{
        localStorage.setItem('token','Bearer '+response.headers.get('token'));
        console.log("token set");
      },
      error=>{
        this.error=error;
        console.log(this.error);
      }
    );

  }

}
