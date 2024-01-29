import { Component, OnInit } from '@angular/core';
import { Prod } from '../../common/prod';
import { ProdService } from '../../services/prod.service';
import { catchError, from, of, throwError, Observable } from 'rxjs';

import { ActivatedRoute } from '@angular/router';
import { CartsService } from '../../services/carts.service';
import {Cart } from '../../common/cart';
import { WatchlistService } from '../../services/watchlist.service';
import { Newwatchlist } from '../../common/newwatchlist';

@Component({
  selector: 'app-producthome',
  templateUrl: './producthome.component.html',
  styleUrls: ['./producthome.component.css']
})
export class ProducthomeComponent implements OnInit{
    products: Prod[] = [];
    names:string[] =[];

    constructor(private productService: ProdService) { }

    ngOnInit(): void {
   
      this.productService.getProducts().subscribe((products) => {
        this.products = products;
       });
    }
}
