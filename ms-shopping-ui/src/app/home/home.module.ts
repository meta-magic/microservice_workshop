import { OrderSummary } from './../cart/ordersummary/ordersummary.component';
import { RouteGaurd } from './../route-gaurd/route.gaurd';
import { ProductCatlogComponent } from './../product/productcatlog.component';
import { FormsModule } from '@angular/forms';
import { OrderComponent } from './../order/order.component';
import { CartComponent } from './../cart/cart.component';
import { ProductComponent } from './../product/product.component';
import { RouterModule } from '@angular/router';
import { AmexioWidgetModule } from 'amexio-ng-extensions';
import { CommonModule } from '@angular/common';
import { NgModule } from "@angular/core";
import { HomeComponent } from "./home.component";
import {PageNotFoundComponent} from "./../page_not_found/pagenotfound.component";
@NgModule({
declarations:[
  HomeComponent,
  ProductComponent,
  CartComponent,
  OrderComponent,
  ProductCatlogComponent,
  PageNotFoundComponent,
  OrderSummary
],
imports:[
  CommonModule,
  FormsModule,
  AmexioWidgetModule,
  RouterModule.forChild([
  {
    path:'',component : HomeComponent,
    children:[
      {
        path:'productcatlog',component : ProductCatlogComponent
      },
      {
        path: 'product',canActivate:[RouteGaurd], component : ProductComponent
      },
      {
        path: 'cart',canActivate:[RouteGaurd],component : CartComponent
      },
      {
        path: 'order', canActivate:[RouteGaurd],component : OrderComponent
      },
      {
        path:'order_summary',canActivate:[RouteGaurd],component:OrderSummary
      }
      // ,
      // {
      //   path:'**', component: PageNotFoundComponent
      // }
    ]
  }
 ])
],
providers:[RouteGaurd],
exports:[RouterModule]
})
export class HomeModule{

}
