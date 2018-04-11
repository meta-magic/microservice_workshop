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
    
    constructor(private http: HttpClient, private router:Router){
        this.servermsg = [];
    }

    ngOnInit() {
        this.fetchData();
    }

    fetchData() {
        let req = {
            "customerId" : "0123456789",
            "cartId" : "0987654321",
        };

        const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8');
        let responsedata: any;
        this.http.post("api/sc/shoppingcart/read/fecthcart",req, { headers }).subscribe(
            response => {
                responsedata = response;
            },
            error => {
            },
            () => {
                this.setData(responsedata);
            }
        );
    }

    setData(responsedata: any) {
        debugger;
        if (responsedata && responsedata.success) {
            this.showcart = true;
            this.data = responsedata.response.products;

        } else {
            this.showcart = false;
            this.msg = responsedata.message;
        }
    }

    removeItem(node:any){

        let req = {
            "customerId" : "0123456789",
            "cartId" : "0987654321",
            "itemId":node.id,
            "itemName" : node.name,
            "quantity" : node.quantity,
            "price" : node.price
        };

        const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8');
        let responsedata: any;
        this.http.post("api/sc/shoppingcart/write/removeitem",req, { headers }).subscribe(
            response => {
                responsedata = response;
            },
            error => {
            },
            () => {
                this.fetchData();
            }
        );
    }    

    checkout(){
        debugger;
        let req = {
            "customerId" : "0123456789",
            "cartId" : "0987654321"           
        };

        const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8');
        let responsedata: any;
        this.http.post("api/sc/shoppingcart/write/placeorder",req, { headers }).subscribe(
            response => {
                responsedata = response;
            },
            error => {
                this.servermsg.push("Not able to place order!");
            },
            () => {
                this.servermsg.push("Order placed successfully!");
                this.router.navigate(['order']);
            }
        );
    }

}
