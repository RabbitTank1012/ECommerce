package com.dbn.onlineshopping.service;
import com.dbn.onlineshopping.dao.WatchListDao;
import com.dbn.onlineshopping.domain.response.WatchListResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class WatchListService {
    private final WatchListDao watchListDao;

    public WatchListService(WatchListDao watchListDao) {
        this.watchListDao = watchListDao;
    }

    public List<WatchListResponse> getAllWatchListProducts(){
        return watchListDao.getAllWatchListProducts();
    }

    public String addWatchList(int productid){
        return watchListDao.addWatchList(productid);
    }

    public String deleteWatchlist(int productid){

        return watchListDao.deleteWatchlist( productid);
    }
}
