import { HttpRequest } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Injectable } from '@angular/core';
import { HttpHandler } from '@angular/common/http';
import { HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { HttpInterceptor } from '@angular/common/http';
export const TOKENKEY='tokenid';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor{
  constructor(private cookieService:CookieService){

  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    debugger;
    const changedReq = req.clone({headers : req.headers.set(TOKENKEY, this.getTokenCookieValue())});
    return next.handle(changedReq);
  }

  getTokenCookieValue(): string {
    const allCookie = this.cookieService.get(TOKENKEY);
    return allCookie;
  }

}
