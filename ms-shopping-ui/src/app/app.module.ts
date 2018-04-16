import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AmexioWidgetModule} from 'amexio-ng-extensions';
import { CommonDataService} from 'amexio-ng-extensions';
import { IconLoaderService} from 'amexio-ng-extensions';
import { CookieService } from 'ngx-cookie-service';

import { AppComponent } from './app.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { APP_ROUTE } from './app.routes';
import { ProductCatlogComponent } from './product/productcatlog.component';
import { ProductComponent } from './product/product.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import { LoginComponent } from './login/login.comonent';
import { RouteGaurd } from './route-gaurd/route.gaurd';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AmexioWidgetModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    RouterModule.forRoot(APP_ROUTE,{useHash:true})
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
