import { Component, OnInit } from '@angular/core';
import { Prod } from '../../common/prod';
import { ProdService } from '../../services/prod.service';
import { ActivatedRoute } from '@angular/router';
import { CartsService } from '../../services/carts.service';
import {Cart } from '../../common/cart';

@Component({
  selector: 'app-prod-detail',
  templateUrl: './prod-detail.component.html',
  styleUrls: ['./prod-detail.component.css']
})
export class ProdDetailComponent  implements OnInit {
  
  product: Prod;
  Authenticated = localStorage.getItem('token');
  Admin = localStorage.getItem('role');
  isAdmin :boolean = true;

  constructor(private productService: ProdService,
    private cartService: CartsService,
    private route: ActivatedRoute) { }
  
    ngOnInit(): void {
      if( this.Authenticated  && this.Admin == "admin"){
        this.isAdmin =true;
      }
      else{
        this.isAdmin=false;
      }
      
      this.route.paramMap.subscribe(() => {
        this.handleProductDetails();
      })
    }

    handleProductDetails() {

      // get the "id" param string. convert string to a number using the "+" symbol
      const theProductId: number = +this.route.snapshot.paramMap.get('product_id')!;
      console.log("product_id=",theProductId);
      this.productService.getProduct(theProductId).subscribe(
        data => {
          this.product = data;
        }
      )
   }

   addToCart() {

    console.log(`Adding to cart: ${this.product.name}, ${this.product.retail_price}`);
    const theCartItem = new Cart(this.product);
    this.cartService.addToCart(theCartItem);
    
  }
}
