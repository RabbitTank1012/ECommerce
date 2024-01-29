import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { Routes, RouterModule} from '@angular/router';

import { SearchComponent } from './components/search/search.component';

import { MenuComponent } from './components/menu/menu.component';
import { ProdlistComponent } from './components/prodlist/prodlist.component';
import { CartshowComponent } from './components/cartshow/cartshow.component';
import { CartdetailComponent } from './components/cartdetail/cartdetail.component';
import { FrequentRecentProductComponent } from './components/frequent-recent-product/frequent-recent-product.component';
import { ProdDetailComponent } from './components/prod-detail/prod-detail.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { OrderDetailComponent } from './components/order-detail/order-detail.component';
import { OrderListAdminComponent } from './components/order-list-admin/order-list-admin.component';
import { ProdlistadminComponent } from './components/prodlistadmin/prodlistadmin.component';
import { CreateProductFormComponent } from './components/create-product-form/create-product-form.component';
import { PopularProfitProductComponent } from './components/popular-profit-product/popular-profit-product.component';
import { WatchlistDetailComponent } from './components/watchlist-detail/watchlist-detail.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UpdateProductFormComponent } from './components/update-product-form/update-product-form.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from './services/interceptor.service';
import { LoginComponent } from './components/login/login.component';
import { AuthGuardService } from './services/auth-guard.service';
import { RegisterComponent } from './components/register/register.component';
import { LoginpageComponent } from './components/loginpage/loginpage.component';
import { ProducthomeComponent } from './components/producthome/producthome.component';


const routes: Routes = [
  {path: 'watchlist', component: WatchlistDetailComponent,canActivate: [AuthGuardService]},
  {path: 'popularprofitproducts', component: PopularProfitProductComponent,canActivate: [AuthGuardService]},
  {path: 'freqentrecentproducts', component: FrequentRecentProductComponent,canActivate: [AuthGuardService]},
  {path: 'product', component: ProdlistComponent,canActivate: [AuthGuardService]},
  // {path: 'productadmin', component: ProdlistadminComponent},
  {path: 'product/:product_id', component: ProdDetailComponent,canActivate: [AuthGuardService]},
  {path: 'updateproduct/:product_id', component: UpdateProductFormComponent,canActivate: [AuthGuardService]},
  {path: 'cart-details', component: CartdetailComponent,canActivate: [AuthGuardService]},
  {path: 'order', component: OrderListComponent,canActivate: [AuthGuardService]},
  {path: 'order/:order_id', component: OrderDetailComponent,canActivate: [AuthGuardService]},
  {path: 'orderadmin', component: OrderListAdminComponent,canActivate: [AuthGuardService]},
  {path: 'createproduct', component: CreateProductFormComponent ,canActivate: [AuthGuardService]},
  {path: 'login', component: LoginComponent },
  {path: 'loginpage', component: LoginpageComponent},
  {path: 'register', component: RegisterComponent },
  {path: 'producthome', component: ProducthomeComponent },
  {path: '', redirectTo: '/producthome', pathMatch: 'full'},
  // {path: '', redirectTo: '/producthome', pathMatch: 'full'},
  // {path: '**', redirectTo: '/producthome', pathMatch: 'full'},
  {
    path: 'productadmin',
    component: ProdlistadminComponent,
    canActivate: [AuthGuardService],
  }
  
];
@NgModule({
  declarations: [
    AppComponent,
    
    SearchComponent,
    MenuComponent,
    ProdlistComponent,
    CartshowComponent,
    CartdetailComponent,
    FrequentRecentProductComponent,
    ProdDetailComponent,
    OrderListComponent,
    OrderDetailComponent,
    OrderListAdminComponent,
    ProdlistadminComponent,
    CreateProductFormComponent,
    PopularProfitProductComponent,
    WatchlistDetailComponent,
    UpdateProductFormComponent,
    LoginComponent,
    RegisterComponent,
    LoginpageComponent,
    ProducthomeComponent
    
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
   
  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true }, AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
