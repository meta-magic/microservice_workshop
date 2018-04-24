import { CookieService } from 'ngx-cookie-service';
import { Component, OnInit } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";

@Component({
    selector : "order",
    templateUrl : "./order.component.html"
})
export class OrderComponent implements OnInit{

    data: any;
    showorders: boolean;
    msg : string;
    msgData:any[]=[];
    showErrorDialog:boolean;
    constructor(private http : HttpClient, private router: Router,private cookieService:CookieService){

    }

    ngOnInit(){
        this.fetchData();
    }

    fetchData() {
        this.msg = "";
        const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8').append('tokenid',this.cookieService.get('tokenid'));
        let responsedata: any;
        this.http.get("api/od/order/query/orderhistory", { headers }).subscribe(
            response => {
                responsedata = response;
            },
            error => {
              this.msgData.push('Enable to connect to server.');
              this.showErrorDialog=true;
            },
            () => {
                this.setData(responsedata);
            }
        );
    }

    setData(responsedata: any) {
        if (responsedata && responsedata.success) {
            this.showorders = true;
            this.data = responsedata.response;

        } else {
          this.msgData.push(responsedata.message);
          this.showErrorDialog=true;
        }
    }

    close(){
      this.showErrorDialog=false;
      this.msgData=[];
    }
}
