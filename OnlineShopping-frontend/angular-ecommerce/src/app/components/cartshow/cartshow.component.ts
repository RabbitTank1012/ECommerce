import { Component, OnInit } from '@angular/core';
import { CartsService } from '../../services/carts.service';

@Component({
  selector: 'app-cartshow',
  templateUrl: './cartshow.component.html',
  styleUrls: ['./cartshow.component.css']
})
export class CartshowComponent implements OnInit{

  totalPrice: number = 0.00;
  totalQuantity: number = 0;

  constructor(private cartService: CartsService) { }
  ngOnInit(): void {
    this.updateCartStatus();
  }

  updateCartStatus() {

    // subscribe to the cart totalPrice
    this.cartService.totalPrice.subscribe(
      data => {
        this.totalPrice = data;
        console.log('totalPrice has changed');
      }
    
    );

    // subscribe to the cart totalQuantity
    this.cartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );

  }

}
