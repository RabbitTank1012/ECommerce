import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Userlogin } from '../../common/userlogin';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Loginresponse } from '../../common/loginresponse';


@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit{
  userLogin:Userlogin;
  loginUserForm: FormGroup;
  loginResponse:Loginresponse;
  username:string;
  
  
  constructor(private userService: UserService,
    private route:  Router) {
     this.loginUserForm = new FormBuilder().group({
       userName: ['', Validators.required],
       userPassword: ['', Validators.required]
              
     });
   } 
   
   ngOnInit(): void { 
   }
   
   loginSubmit(){

     if (this.loginUserForm.valid) {
       const loginUser = this.loginUserForm.value;
       this.userLogin = new Userlogin(loginUser.userName,
       loginUser.userPassword);
       this.username = loginUser.userName;
       this.userService.LoginUser(this.userLogin).subscribe((loginResponse) => {
          this.loginResponse =loginResponse;
          console.log("loginresponse:",loginResponse);
          if (this.loginResponse.message == "invalid")
              alert('Invalid user, please login again');
          else{
              
            localStorage.setItem('token', this.loginResponse.token);
            localStorage.setItem('role', this.loginResponse.message);
            
             
            console.log("Token:",this.loginResponse.token);
            console.log("role:",this.loginResponse.message);
            const role = this.loginResponse.message;
            if( role == "admin")
              this.route.navigateByUrl(`/productadmin`);
            else
              this.route.navigateByUrl(`/product`);
              
          }

          if(this.loginResponse.message == 'admin'){
            this.userService.isAdmin = true;
            this.userService.username =loginUser.userName;
            this.userService.adminStatus.next(true);
            this.userService.isAuthenticated.next(true);
            this.userService.username.next('Raph');
            console.log("isadmin1:",  this.userService.isAdmin);
            console.log("Username1",  this.userService.username);
          }
          else{
            this.userService.isAdmin = false;
            this.userService.username =loginUser.userName;
            this.userService.adminStatus.next(false);
            this.userService.isAuthenticated.next(true);

            this.userService.username.next("aa");
            console.log("isadmin2:",  this.userService.isAdmin);
            console.log("Username2",  this.userService.username);
            
          }
               
      });  

    }
   }

}