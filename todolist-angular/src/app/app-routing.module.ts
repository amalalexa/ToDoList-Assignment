import { NoPageComponent } from './no-page/no-page.component';
import { AuthGaurdService } from './service/auth-gaurd.service';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from 'src/app/signup/signup.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path:'',component:HomeComponent, canActivate:[AuthGaurdService]
  },
  {
    path:'signup',component:SignupComponent
  },
  {
    path:'login',component:LoginComponent
  },
  {
    path:'home',component:HomeComponent, canActivate:[AuthGaurdService]
  },
  {
    path:'**',component:NoPageComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
