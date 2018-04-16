import { Router } from '@angular/router';
/**
 * Created by sagar on 12/04/18.
 */
import {CanLoad} from "@angular/router";
import { Injectable } from "@angular/core";
import { CookieService } from "ngx-cookie-service";
import { CanActivate } from '@angular/router/src/interfaces';

@Injectable()
export class RouteGaurd implements CanActivate{

  constructor(private cookieService:CookieService,private route:Router){

  }
  canActivate(){
    if(this.cookieService.get('tokenid')){
      return true;
    }else {
      this.route.navigate(['login']);
      return false;
    }
  }

  }
