import { CookieService } from 'ngx-cookie-service';
import { OnInit, Component } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";


@Component({
    selector: "product-catlog",
    templateUrl: "./productcatlog.component.html"
})
export class ProductCatlogComponent implements OnInit {

    data: any;
    showproducts: boolean;
    msg : string;
    servermsg : any[];
    msgData:any=[];
    showErrorDialog:boolean;
    constructor(private http: HttpClient, private router: Router,private cookieService:CookieService) {
        this.servermsg = [];
    }

    ngOnInit() {
        this.fetchData();
    }
    close(){
      this.showErrorDialog=false;
      this.msgData=[];
     }

    fetchData() {
        const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8');
        let responsedata: any;
        this.http.get("api/pd/product/query/findall", { headers }).subscribe(
            response => {
                responsedata = response;
            },
            error => {
              this.msgData.push('Enable to connect to server.');
              this.showErrorDialog=true;
            },
            () => {
              if (responsedata && responsedata.success) {
                this.showproducts = true;
                this.data = responsedata.response;

            } else {
              this.msgData.push(responsedata.message);
              this.showErrorDialog=true;
             }
            }
        );
    }

    addToCart(node:any){
        let req = {
            "itemId" : node.id,
            "itemName" : node.name,
            "quantity" : 1,
            "price" : node.price
        }

        const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8').append('tokenid',this.cookieService.get('tokenid'));
        let responsedata: any;
        this.http.post("api/sc/shoppingcart/write/additem",req, { headers }).subscribe(
            response => {
                responsedata = response;
            },
            error => {
              this.msgData.push('Enable to connect to server.');
              this.showErrorDialog=true;
            },
            () => {
              this.validateCart(responsedata);
            }
        );

    }
    validateCart(responsedata:any){
        if(responsedata.success && responsedata.message){
          this.servermsg.push(responsedata.message);
        }else {
          this.msgData.push(responsedata.message);
          this.showErrorDialog=true;
        }
    }
}
