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
      this.username=localStorage.getItem('username');
      if(this.username)
        this.userService.subject.next(true);
      else
        this.userService.subject.next(false);
   }

  ngOnInit(): void {
  }
  getUserName(){
    return localStorage.getItem('username');
  }
  logout(){

    localStorage.removeItem('username');
    localStorage.removeItem('token');
    this.userService.subject.next(false);

    this.router.navigateByUrl("/login");

  }
}
