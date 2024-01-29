import { Component, OnInit } from '@angular/core';
import { Prod } from '../../common/prod';
import { ProdService } from '../../services/prod.service';
import { catchError, from, of, throwError, Observable } from 'rxjs';

import { ActivatedRoute } from '@angular/router';
import { CartsService } from '../../services/carts.service';
import {Cart } from '../../common/cart';
import { WatchlistService } from '../../services/watchlist.service';
import { Newwatchlist } from '../../common/newwatchlist';
@Component({
  selector: 'app-prodlist',
  templateUrl: './prodlist.component.html',
  styleUrls: ['./prodlist.component.css']
})
export class ProdlistComponent implements OnInit{
  products: Prod[] = [];
  names:string[] =[];
  constructor(private productService: ProdService,
    private cartService: CartsService,
    private watchlistService: WatchlistService,
    private route: ActivatedRoute) { }
  
  ngOnInit(): void {
   
    this.productService.getProducts().subscribe((products) => {
      this.products = products;
      
      console.log("cc=",products.length);
      for(let i =0 ; i < products.length ; i++){
        console.log("name=",products[i].name);
        this.names.push(products[i].name);
      }
     });
  }

  addToCart(theProduct: Prod) {
    
    console.log(`Adding to cart: ${theProduct.name}, ${theProduct.retail_price}`);

    // TODO ... do the real work
    const theCartItem = new Cart(theProduct);

    this.cartService.addToCart(theCartItem);
  }

  addToWatchlist(theProduct: Prod) {
    
    console.log(`Adding to watchlist: ${theProduct.product_id}, ${theProduct.name}`);
    const theWatchlist = new Newwatchlist(1,theProduct.product_id);
    this.watchlistService.addWatchlist(theProduct.product_id).subscribe();
   // this.orderService.placeOrder(this.purchaseItems).subscribe()
  
  }


}
