import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class InterceptorService  implements HttpInterceptor {
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // Some logic to grab the token
    const TOKEN = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInBlcm1pc3Npb25zIjpbeyJhdXRob3JpdHkiOiJ1c2VyIn1dfQ.rFob15tWnfPpt6nH_MZRWhTRz3XgBdM4V7tPe1vK76w';

    // const headers = req.headers.set('Content-Type', 'application/json');
    // console.log("het:",req.headers.get('Content-Type'))

    // req = req.clone({
    //   setHeaders: {
    //     'Authorization': 'aa'
    //   }
    // });


    // const authReq = req.clone({ setHeaders: { 'Authorization': TOKEN } });
    // console.log("req=",req);
    // return next.handle(authReq);

    //const TOKEN = 'my jwt';

    // HttpHandler to clone our Request Object and append a token to the header
    return next.handle(req.clone({ setHeaders: { 'Authorization':TOKEN } }));

 // console.log("----request----");

   //console.log(req);
  // console.log("--- end of request---");

     // return next.handle(req);
     
    //const headers = req.headers.set('Authorization', TOKEN);
    //request = request.clone({ headers: request.headers.set('Authorization', 'Bearer ' +  token) });
    //const authReq = req.clone({ headers: headers });
   // return next.handle(authReq);

    // HttpHandler to clone our Request Object and append a token to the header
   // return next.handle(req.clone({ setHeaders: {'Authorization': TOKEN } }));
  }

  constructor() {}
}
