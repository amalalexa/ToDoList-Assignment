import { Router } from '@angular/router';
import { UserService } from './../../service/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  
  username:String;
  isLogin;
  constructor(private userService:UserService, private router:Router) {
      this.isLogin=this.userService.subject;
      this.username=localStorage.getItem('token');
      if(this.username)
        this.userService.subject.next(true);
      else
        this.userService.subject.next(false);
   }

  ngOnInit(): void {
  }
  getUserName(){
    return this.getUserInfo(localStorage.getItem('token'))['sub'];
  }
  logout(){
    localStorage.removeItem('token');
    this.userService.subject.next(false);

    this.router.navigateByUrl("/login");

  }
  getUserInfo(token:String) {
    let payload;
    if (token) {
      payload = token.split(".")[1];
      payload = window.atob(payload);
      return JSON.parse(payload);
    } else {
      return null;
    }
  }

}
