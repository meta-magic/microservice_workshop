import { CookieService } from 'ngx-cookie-service';
import { OnInit, Component } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Router } from "@angular/router";



@Component({
    selector : 'product',
    templateUrl :'./product.component.html'
})
export class ProductComponent implements OnInit{
    productModel : ProductModel;
    error : boolean;
    msg : string;
    msgData:any=[];
    showErrorDialog:boolean;
    constructor(private http: HttpClient, private router:Router,private cookieService:CookieService){
        this.productModel = new ProductModel();
    }

    ngOnInit(){

    }

    save(){
        const headers = new HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8').append('tokenid',this.cookieService.get('tokenid'));
        let rsp : any;
        this.http.post("api/pd/product/write/save",this.productModel.toJson(),{headers}).subscribe(
            response =>{
                rsp = response;
            },
            error =>{
              this.msgData.push('Enable to connect to server.');
              this.showErrorDialog=true;
            },
            ()=>{
              if(rsp.success){
                this.error = false;
                this.router.navigate(['home/productcatlog']);
            }else{
                this.error = true;
                this.msgData.push(rsp.message);
                this.showErrorDialog=true;
                this.productModel=new ProductModel();
             }
            }
        );
    }

    close(){
      this.showErrorDialog=false;
      this.msgData=[];
     }
}

export class ProductModel {
    pname: string;
    pdesc: string;
    pprice : number;

    toJson(){
        return {'name':this.pname,'desc':this.pdesc,'price':this.pprice};
    }
}
