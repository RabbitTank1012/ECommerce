import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from '../../common/cart';
import { CartsService } from '../../services/carts.service'
import { OrderService } from '../../services/order.service';
import { Purchase } from '../../common/purchase';
import { Hint } from 'src/app/common/hint';

@Component({
  selector: 'app-cartdetail',
  templateUrl: './cartdetail.component.html',
  styleUrls: ['./cartdetail.component.css']
})
export class CartdetailComponent {
  cartItems: Cart[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;
  purchaseItems: Purchase[] =[] ;
  alertmessage:Hint;

  constructor(private cartService: CartsService, 
    private orderService : OrderService,
    private route:  Router) { }

  ngOnInit(): void {
    this.listCartDetails();
  }

  listCartDetails() {

    // get a handle to the cart items
    this.cartItems = this.cartService.cartItems;

    // subscribe to the cart totalPrice
    this.cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );

    // subscribe to the cart totalQuantity
    this.cartService.totalQuantity.subscribe( 
      data => this.totalQuantity = data
    );

    // compute cart total price and quantity
    this.cartService.computeCartTotals();
  }

  incrementQuantity(theCartItem: Cart) {
    this.cartService.addToCart(theCartItem);
  }

  decrementQuantity(theCartItem: Cart) {
    this.cartService.decrementQuantity(theCartItem);
  }

  remove(theCartItem: Cart) {
    this.cartService.remove(theCartItem);
  }

  orderSubmit(){
    
    for (let i=0 ; i < this.cartItems.length; i++){
      console.log("id=",this.cartItems[i].id);
      console.log("quantity=",this.cartItems[i].quantity);
      this.purchaseItems.push(new Purchase(this.cartItems[i].id,this.cartItems[i].quantity));
    }
    console.log("aa=",this.purchaseItems.length);
    this.orderService.placeOrder(this.purchaseItems).subscribe(message =>{
      this.alertmessage = message;
      this.cartService.removeAll();
      alert(this.alertmessage.message);
    });

    this.cartService.totalPrice.next(0);
    this.cartService.totalQuantity.next(0);
    this.route.navigateByUrl(`/product`);



  }

  doSearch() {
    console.log("Submit Order");
   // this.router.navigateByUrl(`/search/${value}`);
  }
}



