import { UserService } from './../service/user.service';
import { UserSignUpDetails } from './../view/UserSignUpDetails';
import { Component, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {


  user:UserSignUpDetails;
  result:String='';
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
  saveUserDetails(form:FormGroup){
    this.user=form.value;
    this.userService.saveUserDetails(this.user).subscribe(
      response=>{
        this.result=response.valueOf();
      },
      error=>{
        console.log(error);
      }
    );

  }

}
