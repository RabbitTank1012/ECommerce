
import { Component, OnInit } from '@angular/core';
import { Prod } from '../../common/prod';
import { ProdService } from '../../services/prod.service';
import { catchError, from, of, throwError, Observable } from 'rxjs';

import { ActivatedRoute } from '@angular/router';
import { Newproduct } from '../../common/newproduct';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Router } from '@angular/router';

@Component({
  selector: 'app-create-product-form',
  templateUrl: './create-product-form.component.html',
  styleUrls: ['./create-product-form.component.css']
})
export class CreateProductFormComponent  implements OnInit{
  product:Newproduct;
  addProductForm: FormGroup;
    
  constructor(private productService: ProdService,
     private route:  Router) {
      this.addProductForm = new FormBuilder().group({
        productName: ['', Validators.required],
        productDescription: ['', Validators.required],
        productRetailPrice: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]],
        productQuantity: ['', [Validators.required, Validators.pattern(/^\d+/)]],
        productWholePrice: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]]           
      
      });
    } 
    
    ngOnInit(): void { 
    }

    addProduct() {

      if (this.addProductForm.valid) {
        const newProduct = this.addProductForm.value;
        
        this.product = new Newproduct(newProduct.productName,
          newProduct.productDescription,
          newProduct.productRetailPrice,
          newProduct.productQuantity,
          newProduct.productWholePrice);

        console.log("Name=",this.product.name);
        console.log("Description=",this.product.description);
        console.log("RetailPrice",this.product.retail_price);
        console.log("Quantity=",this.product.quantity);
        console.log("WholePrice=", this.product.whole_price);
        this.productService.CreateProduct(this.product).subscribe();  
        this.addProductForm.reset();
      }
      
    //  let  message = this.productService.CreateProduct(this.product).subscribe();     
  
    }
}
