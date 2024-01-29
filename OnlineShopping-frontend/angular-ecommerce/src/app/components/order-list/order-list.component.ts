
import { Component, OnInit } from '@angular/core';
import { Order } from '../../common/order';
import { OrderService } from '../../services/order.service';
import { Hint } from '../../common/hint';
import { catchError, from, of, throwError, Observable } from 'rxjs';



import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent {
  orders: Order[] = [];
  alertmessage:Hint;
  constructor(private orderService: OrderService,
    private route: ActivatedRoute) { }
  
  ngOnInit(): void {
   
      this.orderService.getOrders().subscribe((orders) => {
        this.orders = orders;
        
       });
  }

 CanCelOrder(theOrder: Order,index :number) {
 
     console.log(`CanCel: ${theOrder.date_created}, ${theOrder.order_status}`);
     
     this.orderService.updateorderstatus(theOrder.order_id).subscribe(message => {
     this.alertmessage = message;
     if(this.alertmessage.message.includes("not"))
     {
     alert(this.alertmessage.message);
     }
     else
     this.orders[index].order_status ='cancel';
     });
      
    }

}
