
import { Component, OnInit } from '@angular/core';
import { Prod } from '../../common/prod';
import { ProdService } from '../../services/prod.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Newproduct } from '../../common/newproduct';

@Component({
  selector: 'app-update-product-form',
  templateUrl: './update-product-form.component.html',
  styleUrls: ['./update-product-form.component.css']
})

export class UpdateProductFormComponent implements OnInit{
  product: Prod;
  newProduct: Newproduct;
  addProductForm: FormGroup;
  productId: number;

  constructor(private productService: ProdService,private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.handleUpdateProduct();
    })
   // console.log("name=", this.product.getName());
   // let aa =this.product.getName();

    this.addProductForm = new FormBuilder().group({
     
      productName: ['', Validators.required],
      productDescription: ['', Validators.required],
      productRetailPrice: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]],
      productQuantity: ['', [Validators.required, Validators.pattern(/^\d+/)]],
      productWholePrice: ['', [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]]           
    
    });

    
    
   }

   handleUpdateProduct() {

    const theProductId: number = +this.route.snapshot.paramMap.get('product_id')!;
    this.productId = theProductId;
    console.log("product_id=",theProductId);
    this.productService.getProduct(theProductId).subscribe(
      data => {
        //this.product = data;
        this.product = new Prod(data.product_id,data.name,data.description,data.retail_price,data.quantity,data.whole_price);
        console.log("ds=",this.product.description);
        this.addProductForm.patchValue({
          productName:data.name,
          productDescription:data.description,
          productRetailPrice:data.retail_price,
          productQuantity:data.quantity,
          productWholePrice:data.whole_price
        })
      }
    )
   
   }

   UpdatProduct(){

    if (this.addProductForm.valid) {
      const newProduct = this.addProductForm.value;
      
      this.newProduct = new Newproduct(newProduct.productName,
        newProduct.productDescription,
        newProduct.productRetailPrice,
        newProduct.productQuantity,
        newProduct.productWholePrice);

      console.log("Name=",this.product.name);
      console.log("Description=",this.product.description);
      console.log("RetailPrice",this.product.retail_price);
      console.log("Quantity=",this.product.quantity);
      console.log("WholePrice=", this.product.whole_price);
      this.productService.EditProduct(this.newProduct,this.product.product_id).subscribe();  
      this.addProductForm.reset();
    }
    
   
   }

   updateProfile(){
    
   }


}
