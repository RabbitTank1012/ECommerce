import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Prod } from '../common/prod';
import { Newproduct } from '../common/newproduct';
import { Frequentproduct } from '../common/frequentproduct';
import { Recentproduct } from '../common/recentproduct';
import { Popularproduct } from '../common/popularproduct';
import { Profitproduct } from '../common/profitproduct';

@Injectable({
  providedIn: 'root'
})
export class ProdService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  private baseUrl = 'http://localhost:8080/products/all';
  private baseFrequentUrl = 'http://localhost:8080/products/frequent/3';
  private baseRecentUrl = 'http://localhost:8080/products/recent/3';
  private prodUrl='http://localhost:8080/products/';
  private basePopulartUrl = 'http://localhost:8080/products/popular/3';
  private baseProfitUrl = 'http://localhost:8080/products/profit/3';
  
  
  products: Prod[] = [];
  names:string[] =[];

  constructor(private http: HttpClient) {}

  getProducts(): Observable<Prod[]> {
    
    console.log('${this.baseUrl}');
    return this.http.get<Prod[]>(`${this.baseUrl}`);
  }

  getFrequentProducts(): Observable<Frequentproduct[]> {
    
    console.log('${this.baseFrequentUrl}');
    return this.http.get<Frequentproduct[]>(`${this.baseFrequentUrl}`);
  }
  getRecentProducts(): Observable<Recentproduct[]> {  
    return this.http.get<Recentproduct[]>(`${this.baseRecentUrl}`);
  }

  getPopularProducts(): Observable<Popularproduct[]> {
    
    console.log('${this.basePopularUrl}');
    return this.http.get<Popularproduct[]>(`${this.basePopulartUrl}`);
  }

  getProfitProducts(): Observable<Profitproduct[]> {
    
    console.log('${this.baseProfitUrl}');
    return this.http.get<Profitproduct[]>(`${this.baseProfitUrl}`);
  }


  getProduct(theProductId: number): Observable<Prod> {
    
    // need to build URL based on product id
    const productUrl = `${this.prodUrl}${theProductId}`;
    console.log("productUrl=", productUrl);
    return this.http.get<Prod>(productUrl);
  }

  CreateProduct(product: Newproduct): Observable<Newproduct> {
   // console.log('this.purchaseUrl=',this.purchaseUrl);
    console.log(JSON.stringify(product));
    return this.http.post<Newproduct>( this.prodUrl, product, this.httpOptions);  

  }

  EditProduct(product: Newproduct,id: number): Observable<Newproduct> {
    // console.log('this.purchaseUrl=',this.purchaseUrl);
    const url = `${this.prodUrl}${id}`;

    console.log("update url=",url);
    console.log("update id=",id);

     //console.log(JSON.stringify(Newproduct));
     return this.http.patch<Newproduct>(  url, product, this.httpOptions);  
 
   }

}
