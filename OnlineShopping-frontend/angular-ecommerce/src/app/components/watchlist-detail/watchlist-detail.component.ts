import { Component, OnInit } from '@angular/core';
import { Watchlist } from '../../common/watchlist';
import { WatchlistService } from '../../services/watchlist.service';
import { CartsService } from '../../services/carts.service';
import { catchError, from, of, throwError, Observable } from 'rxjs';
import { Cart } from '../../common/cart';
import { Prod } from '../../common/prod';

import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-watchlist-detail',
  templateUrl: './watchlist-detail.component.html',
  styleUrls: ['./watchlist-detail.component.css']
})
export class WatchlistDetailComponent implements OnInit{
  watchlists: Watchlist[] = [];
  constructor(private watchlistService: WatchlistService,private cartService: CartsService,
    private route: ActivatedRoute) { }
  
    ngOnInit(): void {
        this.watchlistService.getWatchLists().subscribe((watchlists) => {
        this.watchlists = watchlists;
        
      });
    }

    removeFromWatchlist(theWatchlist: Watchlist) {
      console.log("watch_id",theWatchlist.product_id);
      this.watchlistService.deleteWatchlist(theWatchlist.product_id).subscribe();

      const itemIndex = this.watchlists.findIndex( tempCartItem => tempCartItem.product_id === theWatchlist.product_id );

      // if found, remove the item from the array at the given index
      if (itemIndex > -1) {
         this.watchlists.splice(itemIndex, 1);
       }
    
    }

    addToCart(theWatchlist: Watchlist){
       console.log("price:",theWatchlist.retail_price);
       const product = new Prod(theWatchlist.product_id,theWatchlist.product_name,"",theWatchlist.retail_price,1,0);
       const theCartItem = new Cart(product);
       this.cartService.addToCart(theCartItem);

    }


}
