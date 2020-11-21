import { LoginComponent } from './login/login.component';
import { SignupComponent } from 'src/app/signup/signup.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path:'signup',component:SignupComponent
  },
  {
    path:'login',component:LoginComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
