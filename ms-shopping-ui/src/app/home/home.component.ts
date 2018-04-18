import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { Component } from '@angular/core';

@Component({
  selector:'home',
  templateUrl:'./home.component.html'
})

export class HomeComponent{
  constructor(private router:Router,private cookieService :CookieService){
    // this.router.navigate(['home/productcatlog']);
  }
  addProduct(){
    this.router.navigate(['home/product']);
  }

  productCatlog(){
    this.router.navigate(['home/productcatlog']);
  }

  myCart(){
    this.router.navigate(['home/cart']);
  }

  myOrder(){
    this.router.navigate(['home/order']);
  }

  logout(){
    if(this.cookieService.get('tokenid')){
      this.cookieService.delete('tokenid');
      this.router.navigate(['login']);
    }
  }
}
