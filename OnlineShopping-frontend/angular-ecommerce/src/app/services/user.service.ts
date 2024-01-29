import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../common/user';
import { Newuser } from '../common/newuser';
import { Userlogin } from '../common/userlogin';
import {Loginresponse } from '../common/loginresponse';
import { BehaviorSubject, Subject } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class UserService {

  isAdmin :boolean =false;
  adminStatus: Subject<boolean> = new BehaviorSubject<boolean>(false);
  isAuthenticated: Subject<boolean> = new BehaviorSubject<boolean>(false);
  username: BehaviorSubject<string> = new BehaviorSubject<string>('Leo');
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private loginUrl = 'http://localhost:8080/login';
  private registerUrl = 'http://localhost:8080/signup';
 
  user: User;
  
  constructor(private http: HttpClient) {};

 
   RegisterUser(user:Newuser): Observable<Newuser> {
    // console.log('this.purchaseUrl=',this.purchaseUrl);
     console.log(JSON.stringify(user));
     return this.http.post<Newuser>( this.registerUrl, user, this.httpOptions);  

   }

   LoginUser(userlogin:Userlogin): Observable<any> {
     console.log(JSON.stringify(userlogin));
    const  aa= this.http.post<Userlogin>( this.loginUrl, userlogin, this.httpOptions);  
     
     //return this.http.post<Userlogin>( this.loginUrl, userlogin, this.httpOptions);  
     return aa;

   }

}
