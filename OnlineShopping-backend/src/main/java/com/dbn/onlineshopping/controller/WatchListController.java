package com.dbn.onlineshopping.controller;
import com.dbn.onlineshopping.domain.response.Hintmessage;
import com.dbn.onlineshopping.domain.response.WatchListResponse;
import com.dbn.onlineshopping.service.WatchListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("watchlist")
public class WatchListController {
    private final WatchListService watchListService;

    public WatchListController(WatchListService watchListService) {
        this.watchListService = watchListService;
    }

    @GetMapping(("/products/all"))
    public List<WatchListResponse> getWatchListAllProducts() {

        return watchListService.getAllWatchListProducts();
    }


    @PostMapping("/product/{id}")
    public Hintmessage saveProductSuccess(@PathVariable int id){
        String message = watchListService.addWatchList(id);
        return Hintmessage.builder()
                .message(message)
                .build();

    }

    @DeleteMapping("/product/{id}")
    public Hintmessage deleteProductById(@PathVariable int id) {

        String message = watchListService.deleteWatchlist(id);
        return Hintmessage.builder()
                .message(message)
                .build();

    }


}
