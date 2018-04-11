import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders, HttpRequest} from "@angular/common/http";
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'app';

  constructor(private router:Router)
  {
      
  }

  ngOnInit(){
    
  }
  
 
  addProduct(){
    this.router.navigate(['product']);
  }

  productCatlog(){
    this.router.navigate(['productcatlog']);
  }

  myCart(){
    this.router.navigate(['cart']);
  }

  myOrder(){
    this.router.navigate(['order']);
  }
}
