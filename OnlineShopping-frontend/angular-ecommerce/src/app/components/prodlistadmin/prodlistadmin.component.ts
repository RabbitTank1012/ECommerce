
import { Component, OnInit } from '@angular/core';
import { Prod } from '../../common/prod';
import { ProdService } from '../../services/prod.service';

import { catchError, from, of, throwError, Observable } from 'rxjs';

import { ActivatedRoute } from '@angular/router';
import { Newproduct } from '../../common/newproduct';

import { Router } from '@angular/router';


@Component({
  selector: 'app-prodlistadmin',
  templateUrl: './prodlistadmin.component.html',
  styleUrls: ['./prodlistadmin.component.css']
})
export class ProdlistadminComponent implements OnInit{
 
  products: Prod[] = [];
  product:Newproduct;
  
 // constructor(private router: Router) { };
  constructor(private productService: ProdService,
    private route:  Router) { }

    // constructor(private productService: ProdService,
    //   private route: ActivatedRoute) { }

    ngOnInit(): void {
   
      this.productService.getProducts().subscribe((products) => {
        this.products = products;
        
        console.log("new product=",products.length);
      });
    }

    addProduct() {
      
      this.route.navigateByUrl(`/createproduct`);     
  
    }

    EditProduct(theProduct: Prod) {
       
      this.route.navigateByUrl(`/updateproduct/${theProduct.product_id}`);     
      // theProduct.quantity =11;
      // let temp= new Newproduct(theProduct.name,theProduct.description,theProduct.retail_price,theProduct.quantity,theProduct.whole_price);
      // this.productService.EditProduct(temp,theProduct.product_id).subscribe();     
      
    }
  

}
