import { CookieService } from 'ngx-cookie-service';
import { OnInit, Component } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";



@Component({
    selector : 'cart',
    templateUrl :'./cart.component.html'
})
export class CartComponent implements OnInit{

    data: any;
    showcart: boolean;
    msg : string;
    servermsg : any[];
    msgData:any=[];
    showErrorDialog:boolean;
    total:number;
    constructor(private http: HttpClient, private router:Router,private cookieService:CookieService){
        this.servermsg = [];
    }

    close(){
      this.showErrorDialog=false;
      this.msgData=[];
     }

    ngOnInit() {
        this.fetchData();
    }

    fetchData() {
        let req = {
        };
           let responsedata: any;
           const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8').append('tokenid',this.cookieService.get('tokenid'));
        this.http.post("api/sc/shoppingcart/read/fecthcart",req, { headers }).subscribe(
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
            this.showcart = true;
            if(responsedata.response.total){
              this.total=responsedata.response.total;
            }

            if(responsedata.response.products){
              this.data = responsedata.response.products;

            }

        } else {
            this.showcart = false;
            this.msg = responsedata.message;
        }
    }

    removeItem(node:any){

        let req = {
            "itemId":node.id,
            "itemName" : node.name,
            "quantity" : node.quantity,
            "price" : node.price
        };

        const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8').append('tokenid',this.cookieService.get('tokenid'));
        let responsedata: any;
        this.http.post("api/sc/shoppingcart/write/removeitem",req, { headers }).subscribe(
            response => {
                responsedata = response;
            },
            error => {
              this.msgData.push('Enable to connect to server.');
              this.showErrorDialog=true;
            },
            () => {
                this.fetchData();
            }
        );
    }

    checkout(){
      const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8').append('tokenid',this.cookieService.get('tokenid'));
        let responsedata: any;
        this.http.post("api/sc/shoppingcart/write/placeorder",{}, { headers }).subscribe(
            response => {
                responsedata = response;
            },
            error => {
              this.msgData.push('Enable to connect to server.');
              this.showErrorDialog=true;
            },
            () => {
                this.servermsg.push("Order placed successfully!");
                this.router.navigate(['home/order']);
            }
        );
    }

}
