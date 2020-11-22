import { UserSignUpDetails } from './../view/UserSignUpDetails';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable()
export class UserService {

  constructor(private http:HttpClient) { }

  saveUserDetails(user:UserSignUpDetails){
    
    return this.http.post(environment.apiUrl+"/api/user/signup",user,{ responseType: 'text' });
  }

  login(user:UserSignUpDetails){
    return this.http.post(environment.apiUrl+"/login",user,{observe: 'response'});
  }
}
