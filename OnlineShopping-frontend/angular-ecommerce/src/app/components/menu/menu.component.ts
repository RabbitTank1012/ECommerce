import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit{

  // isAuthenticated: boolean = false;
 // isAdmin: boolean = false;
  
 Authenticated = localStorage.getItem('token');
 Admin = localStorage.getItem('role');
 isAdmin = true;
 username="";
  
 constructor(private userService: UserService, private changeRef: ChangeDetectorRef){
 this.isAdmin =this.userService.isAdmin;
 }
 ngOnInit(): void {
 
 this.userService.adminStatus.subscribe(
  data => {
   this.isAdmin = data;
   console.log('tisAdmin has changed');
   }
   );
   this.userService.username.subscribe(
   data => {
   this.username = data;
   console.log('username has changed');
   }
   );
   
   console.log("Authenticated=",this.Authenticated);
   console.log("menuAdmin =",this.isAdmin );
   
  }
  
  }
  
