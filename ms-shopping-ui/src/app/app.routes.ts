
import {Routes} from '@angular/router';
import { ProductCatlogComponent } from './product/productcatlog.component';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';

export const APP_ROUTE: Routes = [
    {
        path: '', redirectTo: 'productcatlog', pathMatch: 'full'
    }, 
    {
        path: 'productcatlog', component : ProductCatlogComponent
    },
    {
        path: 'product', component : ProductComponent
    },
    {
        path: 'cart', component : CartComponent
    }
]