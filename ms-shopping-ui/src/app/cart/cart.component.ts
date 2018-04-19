import { CookieService } from 'ngx-cookie-service';
import { OnInit, Component } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";



@Component({
    selector: 'cart',
    templateUrl: './cart.component.html'
})
export class CartComponent implements OnInit {

    data: any;
    showcart: boolean;
    msg: string;
    servermsg: any[];
    msgData: any = [];
    showErrorDialog: boolean;
    total: number;
    number: number;
    showOrdersDialogue: boolean = false;

    constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {
        this.servermsg = [];
    }

    //Method to close Error Dialogue box
    close() {
        this.showErrorDialog = false;
        this.msgData = [];
    }

    ngOnInit() {
        this.fetchData();
    }

    //Method to fetch Items that are added in the cart
    fetchData() {
        let req = {
        };
        let responsedata: any;
        const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8').append('tokenid', this.cookieService.get('tokenid'));
        this.http.post("api/sc/shoppingcart/read/fecthcart", req, { headers }).subscribe(
            response => {
                responsedata = response;
            },
            error => {
                this.msgData.push('Unable to connect to server.');
                this.showErrorDialog = true;
            },
            () => {
                this.setData(responsedata);
            }
        );
    }

    setData(responsedata: any) {
        if (responsedata && responsedata.success) {

            if (responsedata.response.products.length > 0) {
                this.data = responsedata.response.products;
                this.number = responsedata.response.products.length;
                this.total = responsedata.response.total;
                this.showcart = true;
            }
            else {
                this.showcart = false;
                this.number = 0;
            }

        } else {

            this.msg = responsedata.message;
        }
    }
    
    //Method to remove items from cart
    removeItem(node: any) {
        let req = {
            "itemId": node.id,
            "itemName": node.name,
            "quantity": node.quantity,
            "price": node.price
        };

        const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8').append('tokenid', this.cookieService.get('tokenid'));
        let responsedata: any;
        this.http.post("api/sc/shoppingcart/write/removeitem", req, { headers }).subscribe(
            response => {
                responsedata = response;
            },
            error => {
                this.msgData.push('Enable to connect to server.');
                this.showErrorDialog = true;
            },
            () => {
                this.fetchData();
            }
        );
    }

    //Method to Place Order
    checkout() {
        const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8').append('tokenid', this.cookieService.get('tokenid'));
        let responsedata: any;

        this.http.post("api/sc/shoppingcart/write/placeorder", {}, { headers }).subscribe(
            response => {
                responsedata = response;
            },
            error => {
                this.msgData.push('Enable to connect to server.');
                this.showErrorDialog = true;
            }
            ,
            () => {
                this.showOrdersDialogue = true;
            }
        );
    }

    //Method that navigates to Catalogue Screen
    onContinueShopping() {
        this.router.navigate(['home/productcatlog']);
    }

    //Method that navigates to order screen
    showOrders() {
        this.router.navigate(['home/order']);
    }

    //Method to close the Dialogue box
    cancel() {
        this.showOrdersDialogue = false;
    }

}
