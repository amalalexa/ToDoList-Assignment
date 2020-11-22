import { Router } from '@angular/router';
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
  constructor(private userService:UserService, private router:Router) { }

  ngOnInit(): void {
  }

  form = new FormGroup({
    'name':new FormControl('',[
      Validators.required,
      Validators.pattern("[a-zA-Z0-9]+")]), //simple validation for name
    'password':new FormControl('',[
      Validators.required,
      Validators.pattern("[a-zA-Z@_0-9]*") //simple validation for pasword
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
        this.router.navigateByUrl("/home");
      },
      error=>{
        this.error=error;
        console.log(this.error);
      }
    );

  }

}
