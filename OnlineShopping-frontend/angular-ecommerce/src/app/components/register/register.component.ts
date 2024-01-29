
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Newuser } from '../../common/newuser';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user:Newuser;
  addUserForm: FormGroup;

  constructor(private userService: UserService,
    private route:  Router) {
     this.addUserForm = new FormBuilder().group({
       userName: ['', Validators.required],
       userPassword: ['', Validators.required],
       userEmail: ['', Validators.required],
       userRole: ['', Validators.required]           
     });
   } 
   
   ngOnInit(): void { 
   }

   addUser(){
    if (this.addUserForm.valid) {
      const newUser = this.addUserForm.value;
      
      this.user = new Newuser(newUser.userName,
        newUser.userPassword,
        newUser.userEmail,
        newUser.userRole
        );

      this.userService.RegisterUser(this.user).subscribe();  
      this.addUserForm.reset();
    }
   }

    
}
