import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
/**
 * Created by sagar on 11/04/18.
 * THIS COMPONENT IS USED FOR AUTHENTICATION MECHANISM
 */
import { Component } from '@angular/core';
@Component({
  selector:'login',
  templateUrl:'./login.component.html'
})
export class LoginComponent{
  loginModel:LoginModel;
  showErrorDialog:boolean;
  msgData:any=[];
  constructor(private httpClient:HttpClient,private router:Router,private cookieService:CookieService){
    this.loginModel=new LoginModel();
 }

 //THIS EVENT IS FIRE WHEN USER CLICK ON LOGIN BUTTON
 onLoginClick(){
  //MODEL VALIDATE CALL
  this.validateForm();
  if(this.msgData.length==0){
    let responeData:any
    this.httpClient.post('api/as/auth/authenticate',this.loginModel).subscribe(
     resposne=>{
      responeData=resposne;
     },
     error=>{
      this.msgData.push('Enable to connect to server.');
      this.showErrorDialog=true;
     },
     ()=>{
      if(responeData.success){
        if(responeData.response && responeData.response.tokenId){
          this.cookieService.set('tokenid',responeData.response.tokenId);
          this.router.navigate(['home']);
        }
      }else{
        this.msgData.push(responeData.message);
        this.showErrorDialog=true;
      }
     }
    )
  }
 }


 //THIS METHOD IS USED FOR VALIDATE MODEL FIELDS
 validateForm(){
  if(this.loginModel.userId==null || this.loginModel.userId==''){
    this.msgData.push("Please enter login id");
    this.showErrorDialog=true;
  }
  if(this.loginModel.password==null || this.loginModel.password==''){
    this.msgData.push("Please enter password");
    this.showErrorDialog=true;
  }
}

close(){
  this.showErrorDialog=false;
  this.msgData=[];
 }

 onCreateClick(){
   this.router.navigate(['create_user']);
 }
}

// THIS MODEL IS USED FOR LOGIN
export class LoginModel{
  userId:string;
  password:string;
  constructor(){
  }
}
