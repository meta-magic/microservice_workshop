
import {Routes} from '@angular/router';
import { ProductCatlogComponent } from './product/productcatlog.component';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import { LoginComponent } from './login/login.comonent';
import { RouteGaurd } from './route-gaurd/route.gaurd';
import { UserCreation } from './usercreation/usercreation';

export const APP_ROUTE: Routes = [
    {
        path: '', redirectTo: 'login', pathMatch: 'full'
    },
    {
      path:'login',component:LoginComponent
    },
    {
        path: 'home',canActivate:[RouteGaurd],loadChildren:'./home/home.module#HomeModule'
    },
    {
      path:'create_user',component:UserCreation
    }
]
