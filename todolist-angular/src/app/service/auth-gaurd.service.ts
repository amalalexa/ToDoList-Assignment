import { UserService } from './user.service';
import { Injectable } from '@angular/core';
import { CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGaurdService implements CanActivate{

  constructor(private userService:UserService, private router:Router) { }

  canActivate(route, state: RouterStateSnapshot){

    if(localStorage.getItem('token'))
      return true;
    else{
      console.log(state.url);
      this.router.navigate(['/login'],{queryParams:{'returnUrl':state.url}});
      this.userService.subject.next(false);
      return false;
    }
  }
}
