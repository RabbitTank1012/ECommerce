import { Component, OnInit } from '@angular/core';
import { Order } from '../../common/order';
import { OrderService } from '../../services/order.service';
import { Hint } from '../../common/hint';
import { catchError, from, of, throwError, Observable } from 'rxjs';



import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-order-list-admin',
  templateUrl: './order-list-admin.component.html',
  styleUrls: ['./order-list-admin.component.css']
})
export class OrderListAdminComponent {
  orders: Order[] = [];
  alertmessage:Hint;
  constructor(private orderService: OrderService,
    private route: ActivatedRoute) { }

    ngOnInit(): void {
   
      this.orderService.getOrders().subscribe((orders) => {
        this.orders = orders;
        
       });
  }

  CanCelOrder(theOrder: Order,index:number) {
  
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

     CompleteOrder(theOrder: Order,index:number) {
 
       this.orderService.completeorder(theOrder.order_id).subscribe(message => {
        
       this.alertmessage = message;
       if(this.alertmessage.message.includes("not"))
       {
       alert(this.alertmessage.message);
       }
       else
       this.orders[index].order_status ='completed';
      
       });
       
      }


}
