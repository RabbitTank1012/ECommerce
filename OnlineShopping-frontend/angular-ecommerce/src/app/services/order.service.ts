import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Prod } from '../common/prod';
import { Order } from '../common/order';
import { Orderitem } from '../common/orderitem';
import { Frequentproduct } from '../common/frequentproduct';
import { Recentproduct } from '../common/recentproduct';
import { map } from 'rxjs/operators';
import { Purchase } from '../common/purchase';



@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl = 'http://localhost:8080/orders/all';
  private orderUrl='http://localhost:8080/orders/';
  private purchaseUrl='http://localhost:8080/orders';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  orders: Order[] = [];
  constructor(private http: HttpClient) {};

  getOrders(): Observable<Order[]> {
    
    console.log('${this.baseUrl}');
    return this.http.get<Order[]>(`${this.baseUrl}`);
  }

  getOrderItem(theOrdertId: number): Observable<Orderitem[]> {
    const orderitemUrl = `${this.orderUrl}${theOrdertId}`;
    console.log("orderitemtUrl=", orderitemUrl);
    return this.http.get<Orderitem[]>(`${orderitemUrl}`);

  }

  placeOrder(purchase: Purchase[]): Observable<any> {
    console.log('this.purchaseUrl=',this.purchaseUrl);
    console.log('this.purchaseUrl=',purchase.length);
    console.log(JSON.stringify(purchase));
    return this.http.post<Purchase[]>( this.purchaseUrl, purchase, this.httpOptions);  

  }

  /**cancel order */
  updateorderstatus(id: number): Observable<any> {
    const url = `${this.orderUrl}/${id}/cancel`;

    return this.http.patch<Order>(url, this.httpOptions);
  }

  /**complete order */
  completeorder(id: number): Observable<any> {
    const url = `${this.orderUrl}/${id}/complete`;

    return this.http.patch<Order>(url, this.httpOptions);
  }

}
