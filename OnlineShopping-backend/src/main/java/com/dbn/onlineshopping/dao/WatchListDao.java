package com.dbn.onlineshopping.dao;
import com.dbn.onlineshopping.domain.entity.WatchList;
import com.dbn.onlineshopping.domain.response.WatchListResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WatchListDao {
    @Autowired
    SessionFactory sessionFactory;
    private final UserDao userDao;

    public WatchListDao(UserDao userDao) {
        this.userDao = userDao;
    }


public List<WatchListResponse> getAllWatchListProducts(){
    Session session;
    List<WatchListResponse> products = new ArrayList<>();
    try{
        session = sessionFactory.getCurrentSession();
        String sql = "select watchlist.watch_id,product.id, product.name from  WatchList watchlist, Product product  " +
                "where  watchlist.product_id = product.product_id and watchlist.user_id = :id ";

        Query query = session.createQuery(sql);
        query.setParameter("id", 1);
        List<Object> resultList =query.list();

        for (int i = 0; i < resultList.size(); i++) {
            Object[] obj = (Object[])resultList.get(i);
            products.add(new WatchListResponse((Integer)obj[0],(Integer)obj[1],(String)obj[2]));
        }
    }
    catch (Exception e){
        e.printStackTrace();
    }

    return products;
}

    public String addWatchList(int productid){
        Session session;
        String message ="" ;
        try{
            session = sessionFactory.getCurrentSession();
            int size = getProductFromWatchlistById(productid).size();
            if (size <1 ){
                WatchList watchList = new WatchList(1,productid);
                session.save(watchList);
                message = " New product has been added";
            }
            else{
                message = " Product was existed";
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  message;
    }

    public String deleteWatchlist(int productid){
        Session session;
        String message="";
        try{
            session = sessionFactory.getCurrentSession();
            List<WatchList> resultList = getProductFromWatchlistById(productid);
            if (resultList.size()>0){
                for( WatchList w : resultList){
                    session.delete(w);
                }
                message = "Product has been deleted";
            }
            else{
                message = " Product was not existed";
            }

        }
        catch (Exception e){
            throw e;
        }
        return message;
    }

    public  List<WatchList> getProductFromWatchlistById(int productid){
        Session session;
        List<WatchList> resultList;
        try{
            session = sessionFactory.getCurrentSession();

            TypedQuery<WatchList> query = session.createQuery(
                    "from WatchList watchlist where watchlist.product_id = :productid  and watchlist.user_id= :userid",
                    WatchList.class);
            query.setParameter("productid", productid);
            query.setParameter("userid", 1);
            resultList = query.getResultList();

        }
        catch (Exception e){
            throw e;
        }

        return resultList;
    }


}
