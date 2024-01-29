import { Component, OnInit } from '@angular/core';
import { ProdService } from '../../services/prod.service';

import { Frequentproduct } from '../../common/frequentproduct'
import { Recentproduct } from '../../common/recentproduct'

@Component({
  selector: 'app-frequent-recent-product',
  templateUrl: './frequent-recent-product.component.html',
  styleUrls: ['./frequent-recent-product.component.css']
})
export class FrequentRecentProductComponent implements OnInit{
  frecentproducts: Frequentproduct[] = [];
  recentproducts: Recentproduct[] = [];
   constructor(private productService: ProdService) { }

    ngOnInit(): void {

      this.productService.getFrequentProducts().subscribe((frecentproducts) => {
        this.frecentproducts = frecentproducts;
       });
       
      this.productService.getRecentProducts().subscribe((recentproducts) => {
        this.recentproducts = recentproducts;
       });


    }

}
