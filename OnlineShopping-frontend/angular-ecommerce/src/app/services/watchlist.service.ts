import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Prod } from '../common/prod';
import { Watchlist } from '../common/watchlist';
import { Newwatchlist } from '../common/newwatchlist';

@Injectable({
  providedIn: 'root'
})
export class WatchlistService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  private getUrl = 'http://localhost:8080/watchlist/products/all';
  private addUrl = 'http://localhost:8080/watchlist/product';
  private deleteUrl = 'http://localhost:8080/watchlist/product';

  watchlists: Watchlist[] = [];
  constructor(private http: HttpClient) {};

  getWatchLists(): Observable<Watchlist[]> {
    console.log('${this.getUrl}');
    return this.http.get<Watchlist[]>(`${this.getUrl}`);
  }

  addWatchlist(id:number): Observable<Newwatchlist> {
    const url = `${this.addUrl}/${id}`;
     console.log("add url:",url);
     return this.http.post<Newwatchlist>(url,this.httpOptions);  
 
   }

   deleteWatchlist(id:number): Observable<any> {
    const url = `${this.deleteUrl}/${id}`;
    console.log("del url:",url);
    return this.http.delete<Newwatchlist>(url, this.httpOptions);  

  }
}
