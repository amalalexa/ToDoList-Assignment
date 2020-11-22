
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './widget/navbar/navbar.component';
import { SignupComponent } from './signup/signup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { UserService } from './service/user.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { HttpClientModule } from '@angular/common/http';
import { MatComponentsModule } from './mat-components/mat-components.module';
import { HomeComponent } from './home/home.component';
import { AddComponent } from './task/add/add.component';
import { TaskService } from './service/task.service';
import { UpdateComponent } from './task/update/update.component';
import { NoPageComponent } from './no-page/no-page.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    AddComponent,
    UpdateComponent,
    NoPageComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatComponentsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule 
  ],
  providers: [UserService,{ provide: MAT_DIALOG_DATA, useValue: {} },{ provide: MatDialogRef, useValue: {} },TaskService],
  bootstrap: [AppComponent]
})
export class AppModule { }
