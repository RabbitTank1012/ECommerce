
import { Component, OnInit } from '@angular/core';
import { ProdService } from '../../services/prod.service';

import { Popularproduct } from '../../common/popularproduct'
import {Profitproduct } from '../../common/profitproduct'

@Component({
  selector: 'app-popular-profit-product',
  templateUrl: './popular-profit-product.component.html',
  styleUrls: ['./popular-profit-product.component.css']
})
export class PopularProfitProductComponent implements OnInit {

  popularproducts: Popularproduct[] = [];
  profitproducts: Profitproduct[] = [];
  constructor(private productService: ProdService) { }
  ngOnInit(): void {

    this.productService.getPopularProducts().subscribe((popularproducts) => {
      this.popularproducts = popularproducts;
     });
     
    this.productService.getProfitProducts().subscribe((profitproducts) => {
      this.profitproducts = profitproducts;
     });


  }


}
