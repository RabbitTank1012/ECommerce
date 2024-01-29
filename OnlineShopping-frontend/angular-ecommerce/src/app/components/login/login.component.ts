import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { CartsService } from '../../services/carts.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  isAuthenticated: boolean = false;
  isAdmin: boolean = false;
  userRole:any;
  username: string = 'user4';
  
  constructor(private route:  Router,
    private userService: UserService,
    private carsService: CartsService) { }

  ngOnInit(): void {

    this.userService.adminStatus.subscribe(
      data => {
        this.isAdmin = data;
        console.log('isAdmin has changed');
      }
    );

    this.userService.isAuthenticated.subscribe(
      data => {
        this.isAuthenticated =data;
        console.log('isAuthenticated has changed');
      }
    );

    // this.userService.username.subscribe(
    //   (data:string) => {
    //     this.username =data;
    //     console.log('username has changed:',this.username);
    //   }
    // );

    this.userService.username.subscribe( 
      (val: string) => {
        this.username =val;
        console.log('username has changed:',this.username);
      }
    );

  }

  Login() {

    this.route.navigateByUrl(`/loginpage`);
    
  }

  Logout() {
    localStorage.removeItem('token');
    this.userService.adminStatus.next(false);
    this.userService.isAuthenticated.next(false);
    this.carsService.totalPrice.next(0);
    this.carsService.totalQuantity.next(0);
    this.carsService.removeAll();
    //this.userService.username.next("");
    this.route.navigateByUrl(`/producthome`)

  }
  Register(){
    this.route.navigateByUrl(`/register`); 
     
  }
}

