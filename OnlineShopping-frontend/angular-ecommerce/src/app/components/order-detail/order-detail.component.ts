import { Component, OnInit } from '@angular/core';
import { Orderitem } from '../../common/orderitem';
import { OrderService } from '../../services/order.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent {
  orderItems: Orderitem[];
  Authenticated = localStorage.getItem('token');
  Admin = localStorage.getItem('role');
  isAdmin :boolean = true;

    constructor(private orderService:OrderService,
    private route: ActivatedRoute) { }
  
    ngOnInit(): void {
      if( this.Authenticated  && this.Admin == "admin"){
        this.isAdmin =true;
      }
      else{
        this.isAdmin=false;
      }
      this.route.paramMap.subscribe(() => {
        this.handleOrderDetails();
      })
    }
  
    handleOrderDetails() {

      // get the "id" param string. convert string to a number using the "+" symbol
      const theOrderId: number = +this.route.snapshot.paramMap.get('order_id')!;
      console.log("order_id=",theOrderId);
      this.orderService.getOrderItem(theOrderId).subscribe(
        data => {
          this.orderItems = data;
        }
      )
   }

}
