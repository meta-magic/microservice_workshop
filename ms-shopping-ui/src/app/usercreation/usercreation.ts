import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
  selector:'usercreation',
  templateUrl:'./usercreation.html'
})

export class UserCreation{
  userModel:UserModel;
  msgData:any=[];
  showErrorDialog:boolean;
  constructor(private httpClient:HttpClient,private router:Router){
    this.userModel=new UserModel();
  }

  //THIS EVENT IS FIRE WHEN USER CLICK ON SAVE BUTTON
  onUserSaveClick(){
  //MODEL VALIDATE CALL
  this.validateForm();
  if(this.msgData.length==0){
    let responeData:any
    this.httpClient.post('api/as/user/create',this.userModel).subscribe(
     resposne=>{
      responeData=resposne;
     },
     error=>{
      console.log('Error occured.');
     },
     ()=>{
      if(responeData.success){
        this.router.navigate(['login']);
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
  if(this.userModel.firstName==null || this.userModel.firstName==''){
    this.msgData.push("Please enter first name");
    this.showErrorDialog=true;
  }
  if(this.userModel.lastName==null || this.userModel.lastName==''){
    this.msgData.push("Please enter last name");
    this.showErrorDialog=true;
  }
  if(this.userModel.userId==null || this.userModel.userId==''){
    this.msgData.push("Please enter user id");
    this.showErrorDialog=true;
  }
  if(this.userModel.password==null || this.userModel.password==''){
    this.msgData.push("Please enter password");
    this.showErrorDialog=true;
  }else if(this.userModel.password && this.userModel.password.length>=6 && this.userModel.password.length<=32){
    this.msgData.push("Please enter valida password length");
    this.showErrorDialog=true;
  }

}

close(){
  this.showErrorDialog=false;
  this.msgData=[];
 }
}

export class UserModel{

  firstName:string;
  lastName:string;
  userId:string;
  password:string;
  constructor(){
    this.firstName='';
    this.lastName='';
    this.userId='';
    this.password='';
  }
}
