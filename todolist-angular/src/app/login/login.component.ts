import { ActivatedRoute, Router } from '@angular/router';
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
  result:String='';
  constructor(private userService:UserService, private router:Router,private routeParams:ActivatedRoute) { }

  ngOnInit(): void {
  }

  form = new FormGroup({
    'name':new FormControl('',[
      Validators.required]), //simple validation for name
    'password':new FormControl('',[
      Validators.required]) //sample validation for password
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
        this.userService.subject.next(true);
        this.routeParams.queryParamMap.subscribe((params)=>{
          this.router.navigateByUrl(params.get('returnUrl'));
        })
      },
      error=>{
        this.result="Wrong Credentials !!!";
        
      }
    );

  }

  
}
