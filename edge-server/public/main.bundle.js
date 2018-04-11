webpackJsonp(["main"],{

/***/ "../../../../../src/$$_lazy_route_resource lazy recursive":
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "../../../../../src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "../../../../../src/app/app.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/app.component.html":
/***/ (function(module, exports) {

module.exports = "<amexio-nav [enable-side-nav-position]=\"false\" \n            [title]=\"'Shopping cart'\"\n            [logo]=\"'assets/product.png'\">\n  <amexio-nav-item position-right [type]=\"'link'\" [title]=\"'Add Product'\" [icon]=\"'fa fa-plus fa-lg'\"\n    (onNavItemClick)=\"addProduct()\">\n  </amexio-nav-item>\n  <amexio-nav-item position-right [type]=\"'link'\" [title]=\"'Catlog'\" [icon]=\"'fa fa fa-tasks fa-lg'\"\n    (onNavItemClick)=\"productCatlog()\">\n  </amexio-nav-item>\n  <amexio-nav-item position-right [type]=\"'link'\" [title]=\"'My Cart'\" [icon]=\"'fa fa-shopping-cart fa-lg'\"\n    (onNavItemClick)=\"myCart()\">\n  </amexio-nav-item>\n  <amexio-nav-item position-right [type]=\"'link'\" [title]=\"'My Order'\" [icon]=\"'fa fa-first-order fa-lg'\"\n    (onNavItemClick)=\"myOrder()\">\n  </amexio-nav-item>\n</amexio-nav>\n<br/><br/><br/><br/>    \n\n<amexio-row>\n  <amexio-column [size] =12 >\n      <router-outlet></router-outlet>    \n  </amexio-column>\n</amexio-row>\n\n\n\n"

/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, exports, __webpack_require__) {

"use strict";

var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = __webpack_require__("../../../core/esm5/core.js");
var router_1 = __webpack_require__("../../../router/esm5/router.js");
var AppComponent = (function () {
    function AppComponent(router) {
        this.router = router;
        this.title = 'app';
    }
    AppComponent.prototype.ngOnInit = function () {
    };
    AppComponent.prototype.addProduct = function () {
        this.router.navigate(['product']);
    };
    AppComponent.prototype.productCatlog = function () {
        this.router.navigate(['productcatlog']);
    };
    AppComponent.prototype.myCart = function () {
        this.router.navigate(['cart']);
    };
    AppComponent.prototype.myOrder = function () {
        this.router.navigate(['order']);
    };
    AppComponent = __decorate([
        core_1.Component({
            selector: 'app-root',
            template: __webpack_require__("../../../../../src/app/app.component.html"),
            styles: [__webpack_require__("../../../../../src/app/app.component.css")]
        }),
        __metadata("design:paramtypes", [router_1.Router])
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;


/***/ }),

/***/ "../../../../../src/app/app.module.ts":
/***/ (function(module, exports, __webpack_require__) {

"use strict";

var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var platform_browser_1 = __webpack_require__("../../../platform-browser/esm5/platform-browser.js");
var core_1 = __webpack_require__("../../../core/esm5/core.js");
var amexio_ng_extensions_1 = __webpack_require__("../../../../amexio-ng-extensions/amexio-ng-extensions.es5.js");
var app_component_1 = __webpack_require__("../../../../../src/app/app.component.ts");
var http_1 = __webpack_require__("../../../common/esm5/http.js");
var router_1 = __webpack_require__("../../../router/esm5/router.js");
var app_routes_1 = __webpack_require__("../../../../../src/app/app.routes.ts");
var productcatlog_component_1 = __webpack_require__("../../../../../src/app/product/productcatlog.component.ts");
var product_component_1 = __webpack_require__("../../../../../src/app/product/product.component.ts");
var forms_1 = __webpack_require__("../../../forms/esm5/forms.js");
var common_1 = __webpack_require__("../../../common/esm5/common.js");
var cart_component_1 = __webpack_require__("../../../../../src/app/cart/cart.component.ts");
var order_component_1 = __webpack_require__("../../../../../src/app/order/order.component.ts");
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            declarations: [
                app_component_1.AppComponent,
                productcatlog_component_1.ProductCatlogComponent,
                product_component_1.ProductComponent,
                cart_component_1.CartComponent,
                order_component_1.OrderComponent
            ],
            imports: [
                platform_browser_1.BrowserModule,
                amexio_ng_extensions_1.AmexioWidgetModule,
                forms_1.FormsModule,
                common_1.CommonModule,
                http_1.HttpClientModule,
                router_1.RouterModule.forRoot(app_routes_1.APP_ROUTE, { useHash: true })
            ],
            providers: [],
            bootstrap: [app_component_1.AppComponent]
        })
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;


/***/ }),

/***/ "../../../../../src/app/app.routes.ts":
/***/ (function(module, exports, __webpack_require__) {

"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var productcatlog_component_1 = __webpack_require__("../../../../../src/app/product/productcatlog.component.ts");
var product_component_1 = __webpack_require__("../../../../../src/app/product/product.component.ts");
var cart_component_1 = __webpack_require__("../../../../../src/app/cart/cart.component.ts");
var order_component_1 = __webpack_require__("../../../../../src/app/order/order.component.ts");
exports.APP_ROUTE = [
    {
        path: '', redirectTo: 'productcatlog', pathMatch: 'full'
    },
    {
        path: 'productcatlog', component: productcatlog_component_1.ProductCatlogComponent
    },
    {
        path: 'product', component: product_component_1.ProductComponent
    },
    {
        path: 'cart', component: cart_component_1.CartComponent
    },
    {
        path: 'order', component: order_component_1.OrderComponent
    }
];


/***/ }),

/***/ "../../../../../src/app/cart/cart.component.html":
/***/ (function(module, exports) {

module.exports = "<amexio-notification [data]=\"servermsg\"\n                     [vertical-position]=\"'top'\"\n                     [horizontal-position]=\"'right'\"\n                     [auto-dismiss-msg]=\"true\"\n                     [auto-dismiss-msg-interval]=\"4000\">\n</amexio-notification>\n\n\n<amexio-card [header]=\"true\"\n             [footer]=\"true\"\n             [footer-align]=\"'right'\">\n    <amexio-header>\n        Cart {{msg}} \n    </amexio-header>\n    <amexio-body>\n        \n        <amexio-row>\n            <amexio-column [size] =3 >\n                <b>Name</b>\n            </amexio-column>\n            <amexio-column [size] =3 >\n                <b>Quantity</b>\n            </amexio-column>\n            <amexio-column [size] =3 >\n                <b>Price</b>\n            </amexio-column>\n        </amexio-row>\n        <br/>\n        <amexio-row *ngFor=\"let node of data\">\n            <amexio-column [size] =3 >\n                {{node.name}}\n            </amexio-column>\n            <amexio-column [size] =3 >\n                {{node.quantity}}\n            </amexio-column>\n            <amexio-column [size] =3 >\n                {{node.price}}\n            </amexio-column>\n            <amexio-column [size] =3 >\n                <amexio-button\n                     (onClick)=\"removeItem(node)\"    \n                     [label]=\"'Remove'\"\n                     [type]=\"'red'\"\n                     [tooltip]=\"'Remove'\" >\n                </amexio-button>    \n            </amexio-column>\n        </amexio-row>\n    </amexio-body>\n    <amexio-action>\n        <amexio-button\n             (onClick)=\"checkout()\"\n             [icon]=\"'fa fa-money'\"\n             [label]=\"'Checkout'\"\n             [type]=\"'theme-color'\"\n             [tooltip]=\"'Checkout'\" >\n        </amexio-button>\n    </amexio-action>\n</amexio-card>"

/***/ }),

/***/ "../../../../../src/app/cart/cart.component.ts":
/***/ (function(module, exports, __webpack_require__) {

"use strict";

var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = __webpack_require__("../../../core/esm5/core.js");
var http_1 = __webpack_require__("../../../common/esm5/http.js");
var router_1 = __webpack_require__("../../../router/esm5/router.js");
var CartComponent = (function () {
    function CartComponent(http, router) {
        this.http = http;
        this.router = router;
        this.servermsg = [];
    }
    CartComponent.prototype.ngOnInit = function () {
        this.fetchData();
    };
    CartComponent.prototype.fetchData = function () {
        var _this = this;
        var req = {
            "customerId": "0123456789",
            "cartId": "0987654321",
        };
        var headers = new http_1.HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8');
        var responsedata;
        this.http.post("api/sc/shoppingcart/read/fecthcart", req, { headers: headers }).subscribe(function (response) {
            responsedata = response;
        }, function (error) {
        }, function () {
            _this.setData(responsedata);
        });
    };
    CartComponent.prototype.setData = function (responsedata) {
        debugger;
        if (responsedata && responsedata.success) {
            this.showcart = true;
            this.data = responsedata.response.products;
        }
        else {
            this.showcart = false;
            this.msg = responsedata.message;
        }
    };
    CartComponent.prototype.removeItem = function (node) {
        var _this = this;
        var req = {
            "customerId": "0123456789",
            "cartId": "0987654321",
            "itemId": node.id,
            "itemName": node.name,
            "quantity": node.quantity,
            "price": node.price
        };
        var headers = new http_1.HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8');
        var responsedata;
        this.http.post("api/sc/shoppingcart/write/removeitem", req, { headers: headers }).subscribe(function (response) {
            responsedata = response;
        }, function (error) {
        }, function () {
            _this.fetchData();
        });
    };
    CartComponent.prototype.checkout = function () {
        var _this = this;
        debugger;
        var req = {
            "customerId": "0123456789",
            "cartId": "0987654321"
        };
        var headers = new http_1.HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8');
        var responsedata;
        this.http.post("api/sc/shoppingcart/write/placeorder", req, { headers: headers }).subscribe(function (response) {
            responsedata = response;
        }, function (error) {
            _this.servermsg.push("Not able to place order!");
        }, function () {
            _this.servermsg.push("Order placed successfully!");
            _this.router.navigate(['order']);
        });
    };
    CartComponent = __decorate([
        core_1.Component({
            selector: 'cart',
            template: __webpack_require__("../../../../../src/app/cart/cart.component.html")
        }),
        __metadata("design:paramtypes", [http_1.HttpClient, router_1.Router])
    ], CartComponent);
    return CartComponent;
}());
exports.CartComponent = CartComponent;


/***/ }),

/***/ "../../../../../src/app/order/order.component.html":
/***/ (function(module, exports) {

module.exports = "{{msg}}\n<br/>\n\n<amexio-card [header]=\"true\">\n    <amexio-header>\n        Orders\n    </amexio-header>\n    <amexio-body>\n        <amexio-row>\n            <amexio-column [size] =3 >\n                Order Ref     \n            </amexio-column>\n            <amexio-column [size] =3 >\n                Order Date\n            </amexio-column>\n            <amexio-column [size] =3 >\n                Total\n            </amexio-column>\n            <amexio-column [size] =3 >\n                Status\n            </amexio-column>\n        </amexio-row>\n        <amexio-row *ngFor=\"let node of data\">\n            <amexio-column [size] =3 >\n                {{node.id}}\n            </amexio-column>\n            <amexio-column [size] =3 >\n                {{node.date}}\n            </amexio-column>\n            <amexio-column [size] =3 >\n                {{node.total}}\n            </amexio-column>\n            <amexio-column [size] =3 >\n                {{node.status}}\n            </amexio-column>\n        </amexio-row>\n    </amexio-body>\n    <amexio-action>\n        footer\n    </amexio-action>\n</amexio-card>\n"

/***/ }),

/***/ "../../../../../src/app/order/order.component.ts":
/***/ (function(module, exports, __webpack_require__) {

"use strict";

var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = __webpack_require__("../../../core/esm5/core.js");
var http_1 = __webpack_require__("../../../common/esm5/http.js");
var router_1 = __webpack_require__("../../../router/esm5/router.js");
var OrderComponent = (function () {
    function OrderComponent(http, router) {
        this.http = http;
        this.router = router;
    }
    OrderComponent.prototype.ngOnInit = function () {
        this.fetchData();
    };
    OrderComponent.prototype.fetchData = function () {
        var _this = this;
        this.msg = "";
        var headers = new http_1.HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8');
        var responsedata;
        this.http.get("api/od/order/query/orderhistory", { headers: headers }).subscribe(function (response) {
            responsedata = response;
        }, function (error) {
            _this.msg = "Enabled to connect";
        }, function () {
            _this.setData(responsedata);
        });
    };
    OrderComponent.prototype.setData = function (responsedata) {
        debugger;
        if (responsedata && responsedata.success) {
            this.showorders = true;
            this.data = responsedata.response;
        }
        else {
            this.showorders = false;
            this.msg = responsedata.message;
        }
    };
    OrderComponent = __decorate([
        core_1.Component({
            selector: "order",
            template: __webpack_require__("../../../../../src/app/order/order.component.html")
        }),
        __metadata("design:paramtypes", [http_1.HttpClient, router_1.Router])
    ], OrderComponent);
    return OrderComponent;
}());
exports.OrderComponent = OrderComponent;


/***/ }),

/***/ "../../../../../src/app/product/product.component.html":
/***/ (function(module, exports) {

module.exports = "<amexio-row>\n    <amexio-column [size]=2>\n        \n    </amexio-column>\n    <amexio-column [size]=8>\n        <amexio-card [header]=\"true\" [footer]=\"true\" [footer-align]=\"'right'\">\n            <amexio-header>\n                Add Product  {{msg}}\n            </amexio-header>\n            <amexio-body>\n                <amexio-row>\n                    <amexio-column [size]=12>\n                        <amexio-text-input\n                             [(ngModel)]=\"productModel.pname\" \n                             name=\"pname\" \n                             field-label=\"Name\"\n                             place-holder=\"Please enter product name\"\n                             icon-feedback=\"true\">\n                        </amexio-text-input>\n                    </amexio-column>    \n                </amexio-row>\n                <amexio-row>\n                    <amexio-column [size]=12>\n                        <amexio-textarea-input\n                                [field-label]=\"'Description'\"\n                                [(ngModel)]=\"productModel.pdesc\" \n                                name=\"pdesc\" \n                                [place-holder]=\"'Please enter product description'\"\n                                [allow-blank]=\"true\"\n                                [rows]=\"'2'\"\n                                [columns]=\"'2'\">\n                        </amexio-textarea-input>\n                    </amexio-column>    \n                </amexio-row>\n                <amexio-row>\n                    <amexio-column [size]=12>\n                       <amexio-number-input\n                            [field-label]=\"'Price'\"\n                            [(ngModel)]=\"productModel.pprice\" \n                            name=\"pdesc\"                             \n                            [place-holder]=\"'Please enter price'\">\n                       </amexio-number-input>\n                    </amexio-column>    \n                </amexio-row>\n                </amexio-body>\n            <amexio-action>\n                <amexio-button\n                     [label]=\"'Save'\"\n                     [type]=\"'theme-color'\"\n                     [tooltip]=\"'Save'\"\n                     (click)=\"save()\" >\n                </amexio-button>\n            </amexio-action>\n        </amexio-card>\n    </amexio-column>\n    <amexio-column [size]=2>\n    </amexio-column>\n</amexio-row>\n\n"

/***/ }),

/***/ "../../../../../src/app/product/product.component.ts":
/***/ (function(module, exports, __webpack_require__) {

"use strict";

var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = __webpack_require__("../../../core/esm5/core.js");
var http_1 = __webpack_require__("../../../common/esm5/http.js");
var router_1 = __webpack_require__("../../../router/esm5/router.js");
var ProductComponent = (function () {
    function ProductComponent(http, router) {
        this.http = http;
        this.router = router;
        this.productModel = new ProductModel();
    }
    ProductComponent.prototype.ngOnInit = function () {
    };
    ProductComponent.prototype.save = function () {
        var _this = this;
        debugger;
        var headers = new http_1.HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8');
        var rsp;
        this.http.post("api/pd/product/write/save", this.productModel.toJson(), { headers: headers }).subscribe(function (response) {
            rsp = response;
        }, function (error) {
        }, function () {
            _this.afterSave(rsp);
        });
    };
    ProductComponent.prototype.afterSave = function (response) {
        if (response.success) {
            this.error = false;
            this.router.navigate(['productcatlog']);
        }
        else {
            this.error = true;
            this.msg = response.message;
        }
        debugger;
    };
    ProductComponent = __decorate([
        core_1.Component({
            selector: 'product',
            template: __webpack_require__("../../../../../src/app/product/product.component.html")
        }),
        __metadata("design:paramtypes", [http_1.HttpClient, router_1.Router])
    ], ProductComponent);
    return ProductComponent;
}());
exports.ProductComponent = ProductComponent;
var ProductModel = (function () {
    function ProductModel() {
    }
    ProductModel.prototype.toJson = function () {
        return { 'name': this.pname, 'desc': this.pdesc, 'price': this.pprice };
    };
    return ProductModel;
}());
exports.ProductModel = ProductModel;


/***/ }),

/***/ "../../../../../src/app/product/productcatlog.component.html":
/***/ (function(module, exports) {

module.exports = "<amexio-row>\n    <amexio-column [size]=12>\n        {{msg}}\n    </amexio-column>\n</amexio-row>\n\n<amexio-notification [data]=\"servermsg\"\n                     [vertical-position]=\"'top'\"\n                     [horizontal-position]=\"'right'\"\n                     [auto-dismiss-msg]=\"true\"\n                     [auto-dismiss-msg-interval]=\"4000\">\n</amexio-notification>\n\n\n<amexio-row *ngIf=\"showproducts\">\n    <amexio-column [size]=3 *ngFor=\"let node of data\">\n        <amexio-card [header]=\"true\" [footer]=\"true\">\n            <amexio-header>{{node.name}}</amexio-header>\n            <amexio-body>\n                <amexio-image [path]=\"node.image\" [height]=\"'330px'\" [width]=\"'330px'\"></amexio-image><br/>\n                Price: {{node.price}} <br/>\n                \n            </amexio-body>\n            <amexio-action>\n                <amexio-button\n                     (onClick)=\"addToCart(node)\"\n                     [icon]=\"'fa fa-cart-plus'\"\n                     [label]=\"'Add To Cart'\"\n                     [type]=\"'yellow'\"\n                     [tooltip]=\"'Add to cart'\" >\n                </amexio-button>\n            </amexio-action>\n        </amexio-card>\n    </amexio-column>\n</amexio-row>\n"

/***/ }),

/***/ "../../../../../src/app/product/productcatlog.component.ts":
/***/ (function(module, exports, __webpack_require__) {

"use strict";

var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = __webpack_require__("../../../core/esm5/core.js");
var http_1 = __webpack_require__("../../../common/esm5/http.js");
var router_1 = __webpack_require__("../../../router/esm5/router.js");
var ProductCatlogComponent = (function () {
    function ProductCatlogComponent(http, router) {
        this.http = http;
        this.router = router;
        debugger;
        this.servermsg = [];
    }
    ProductCatlogComponent.prototype.ngOnInit = function () {
        this.fetchData();
    };
    ProductCatlogComponent.prototype.fetchData = function () {
        var _this = this;
        var headers = new http_1.HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8');
        var responsedata;
        this.http.get("api/pd/product/query/findall", { headers: headers }).subscribe(function (response) {
            responsedata = response;
        }, function (error) {
        }, function () {
            _this.setData(responsedata);
        });
    };
    ProductCatlogComponent.prototype.setData = function (responsedata) {
        if (responsedata && responsedata.success) {
            this.showproducts = true;
            this.data = responsedata.response;
        }
        else {
            this.showproducts = false;
            this.msg = responsedata.message;
        }
    };
    ProductCatlogComponent.prototype.addToCart = function (node) {
        var _this = this;
        var req = {
            "customerId": "0123456789",
            "cartId": "0987654321",
            "itemId": node.id,
            "itemName": node.name,
            "quantity": 1,
            "price": node.price
        };
        var headers = new http_1.HttpHeaders().append('Content-Type', 'application/json;charset=UTF-8');
        var responsedata;
        this.http.post("api/sc/shoppingcart/write/additem", req, { headers: headers }).subscribe(function (response) {
            responsedata = response;
        }, function (error) {
        }, function () {
            _this.validateCart(responsedata);
        });
    };
    ProductCatlogComponent.prototype.validateCart = function (responsedata) {
        debugger;
        if (responsedata && responsedata.message) {
            //this.showproducts = false;
            this.msg = responsedata.message;
        }
        else {
            this.servermsg.push('Product added to cart');
            //this.router.navigate(['order']);
        }
    };
    ProductCatlogComponent = __decorate([
        core_1.Component({
            selector: "product-catlog",
            template: __webpack_require__("../../../../../src/app/product/productcatlog.component.html")
        }),
        __metadata("design:paramtypes", [http_1.HttpClient, router_1.Router])
    ], ProductCatlogComponent);
    return ProductCatlogComponent;
}());
exports.ProductCatlogComponent = ProductCatlogComponent;


/***/ }),

/***/ "../../../../../src/environments/environment.ts":
/***/ (function(module, exports, __webpack_require__) {

"use strict";

// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
Object.defineProperty(exports, "__esModule", { value: true });
exports.environment = {
    production: false
};


/***/ }),

/***/ "../../../../../src/main.ts":
/***/ (function(module, exports, __webpack_require__) {

"use strict";

Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = __webpack_require__("../../../core/esm5/core.js");
var platform_browser_dynamic_1 = __webpack_require__("../../../platform-browser-dynamic/esm5/platform-browser-dynamic.js");
var app_module_1 = __webpack_require__("../../../../../src/app/app.module.ts");
var environment_1 = __webpack_require__("../../../../../src/environments/environment.ts");
if (environment_1.environment.production) {
    core_1.enableProdMode();
}
platform_browser_dynamic_1.platformBrowserDynamic().bootstrapModule(app_module_1.AppModule)
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("../../../../../src/main.ts");


/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map