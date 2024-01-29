package com.dbn.onlineshopping.dao;

import com.dbn.onlineshopping.domain.entity.ProductResult;
import com.dbn.onlineshopping.domain.response.ProductFrequentResponse;
import com.dbn.onlineshopping.domain.response.ProductPopularResponse;
import com.dbn.onlineshopping.domain.response.ProductProfitRespose;
import com.dbn.onlineshopping.domain.response.ProductRecentResponse;
import com.dbn.onlineshopping.domain.entity.BaseUserInfo;
import com.dbn.onlineshopping.domain.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {

    @Autowired
    SessionFactory sessionFactory;

    private final UserDao userDao;

    public ProductDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<ProductResult> getAllInStockProducts0(){
        Session session;
        List<ProductResult> productsList = null;
        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ProductResult> cq = cb.createQuery(ProductResult.class);
            Root<Product> root = cq.from(Product.class);
            //cq.select(root);
            cq.multiselect(
                    root.get("product_id"),
                    root.get("description"),
                    root.get("name"),
                    root.get("quantity"),
                    root.get("retail_price")
            );
            Predicate predicate = cb.gt(root.get("quantity"),0);
            cq.where(predicate);
            productsList = session.createQuery(cq).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (productsList.isEmpty()) ? null : productsList;
    }

    public List<String> getAllProducts(){
        Session session;
        List<String> products = new ArrayList<>();

        String userrole = BaseUserInfo.role;
        int user_id= userDao.getUserByname(BaseUserInfo.username).getUser_id();

        try{
            session = sessionFactory.getCurrentSession();
            String sql;
            if(userrole.equals("user")) {
                sql = "select product.id, product.name from Product product  where product.quantity >0 ";
            }
            else{
                sql = "select product.id, product.name from Product product";
            }
            Query query = session.createQuery(sql);
            List<Object> resultList =query.list();

            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[])resultList.get(i);
                products.add((String)obj[1]);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }

    public List<ProductResult> getAllProduct(){
        Session session;
        String userrole = BaseUserInfo.role;
        List<ProductResult> products = new ArrayList<>();
        try{
            session = sessionFactory.getCurrentSession();
            String sql;
            if(userrole.equals("user")) {
                sql = "select product.product_id,product.name,product.description,product.retail_price,product.quantity,product.whole_price " +
                        " from Product product  where product.quantity >0 ";
            }
            else{
                sql = "select product.product_id,product.name,product.description,product.retail_price,product.quantity,product.whole_price " +
                        "from Product product";
            }


            Query query = session.createQuery(sql);
            List<Object> resultList =query.list();

            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[])resultList.get(i);
                ProductResult result  = new ProductResult((int)obj[0],(String)obj[1],(String)obj[2],(double)obj[3],(int)obj[4],(double)obj[5]);
                products.add(result);
                //products.add(new AllProductResponse((String)obj[1]));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }


    public ProductResult GetProductDetailById(int id){
        Session session;
        ProductResult result = null;
        try{
            session = sessionFactory.getCurrentSession();
            String sql = "select product.product_id,product.description,product.name,product.retail_price,product.quantity,product.whole_price from Product product  where product.product_id = :id ";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            List<Object> resultList =query.list();

            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[])resultList.get(i);
                result  = new ProductResult((int)obj[0],(String)obj[1],(String)obj[2],(double)obj[3],(int)obj[4],(double)obj[5]);
             }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  result;

    }

    public Product GetProductDetailById2(int id){
        Session session;
        Product result = null;
        try{
            session = sessionFactory.getCurrentSession();
            String sql = "select product.product_id,product.name,product.description,product.retail_price,product.quantity,product.whole_price from Product product  where product.product_id = :id ";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            List<Object> resultList =query.list();

            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[])resultList.get(i);
                result = new Product((int)obj[0],(String)obj[1],(String)obj[2],(double)obj[3],(int)obj[4],(double)obj[5]);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  result;

    }

    public void addProduct(Product product){
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(product);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateProductById(int id,Product updateProduct){

        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            Product product= session.get(Product.class, id);
            product.setName(updateProduct.getName());
            product.setDescription(updateProduct.getDescription());
            product.setRetail_price(updateProduct.getRetail_price());
            product.setQuantity(updateProduct.getQuantity());
            product.setWhole_price(updateProduct.getWhole_price());
            session.saveOrUpdate(product);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<ProductFrequentResponse> GetMostFrequentlyPurchasedProduct(int num){
        Session session;
        List<ProductFrequentResponse> products = new ArrayList<>();
        String userrole = BaseUserInfo.role;
        int user_id= userDao.getUserByname(BaseUserInfo.username).getUser_id();
        System.out.println("user=" +user_id);

        try{
            session = sessionFactory.getCurrentSession();
            String sql = "select orderitem.product_id, product.name, count(orderitem.product_id) as frequent " +
                    "from Orders order, OrderItem orderitem, Product product  " +
                    "where order.order_id = orderitem.order_id and product.product_id = orderitem.product_id " +
                    "and order.user_id = :id  and order.order_status != 'cancel'" +
                    "group by orderitem.product_id, product.name order by frequent desc";

            org.hibernate.Query query = session.createQuery(sql);
            query.setFirstResult(0);
            query.setMaxResults(num);
            query.setParameter("id", user_id);
            List<Object> resultList =query.list();
            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[])resultList.get(i);
                String aa =  obj[2].toString();
                int frequent = Integer.parseInt(aa);
                ProductFrequentResponse temp = new ProductFrequentResponse((String)obj[1], frequent);
                products.add(temp);
               // System.out.println(obj[0]+","+obj[1] + ","+obj[2]  );
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return products;
    }

    public List<ProductRecentResponse> GetMostRecentlyPurchasedProduct(int num){
        Session session;
        List<ProductRecentResponse> products = new ArrayList<>();

        String userrole = BaseUserInfo.role;
        int user_id= userDao.getUserByname(BaseUserInfo.username).getUser_id();
        try{
            session = sessionFactory.getCurrentSession();

            String sql = "select order.order_id, order.date_created, product.name, order.order_status,orderitem.quantity,orderitem.purchased_price " +
                    "from Orders order, OrderItem orderitem, Product product  " +
                    "where order.order_id = orderitem.order_id and product.product_id = orderitem.product_id " +
                    "and order.user_id = :id  and order.order_status != 'cancel'" +
                    "order by order.date_created desc ";

            org.hibernate.Query query = session.createQuery(sql);
            query.setParameter("id", user_id);
            query.setFirstResult(0);
            query.setMaxResults(num);

            List<Object> resultList =query.list();
            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[])resultList.get(i);
                ProductRecentResponse temp = new ProductRecentResponse((String)obj[2], (String)obj[1]);
                products.add(temp);
                // System.out.println(obj[0]+","+obj[1] + ","+obj[2]  );
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return products;

    }

    public List<ProductProfitRespose> GetMostProfitableProduct(int num){
        Session session;
        List<ProductProfitRespose> products = new ArrayList<>();
        try{
            session = sessionFactory.getCurrentSession();

            String sql = "select orderitem.product_id, product.name, sum((orderitem.purchased_price- orderitem.wholesale_price) * orderitem.quantity) as profit " +
                    "from Orders order, OrderItem orderitem, Product product  " +
                    "where order.order_id = orderitem.order_id and product.product_id = orderitem.product_id " +
                    "and order.order_status = 'completed' " +
                    "group by orderitem.product_id, product.name " +
                    "order by profit desc ";

            org.hibernate.Query query = session.createQuery(sql);
            query.setFirstResult(0);
            query.setMaxResults(num);
            List<Object> resultList =query.list();
            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[])resultList.get(i);

                ProductProfitRespose temp = new ProductProfitRespose((String)obj[1], (Double)obj[2]);
                products.add(temp);
                // System.out.println(obj[0]+","+obj[1] + ","+obj[2]  );
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return products;

    }

    public List<ProductPopularResponse> GetMostPopularPurchasedProduct(int num){
        Session session;
        List<ProductPopularResponse> products = new ArrayList<>();
        try{
            session = sessionFactory.getCurrentSession();
            String sql = "select orderitem.product_id, product.name, sum(orderitem.quantity) as popular " +
                    "from Orders order, OrderItem orderitem, Product product  " +
                    "where order.order_id = orderitem.order_id and product.product_id = orderitem.product_id " +
                    "and order.order_status = 'completed'" +
                    "group by orderitem.product_id, product.name order by popular desc";

            org.hibernate.Query query = session.createQuery(sql);
            query.setFirstResult(0);
            query.setMaxResults(num);
            List<Object> resultList =query.list();
            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[])resultList.get(i);
                String aa =  obj[2].toString();
                int popular = Integer.parseInt(aa);
                ProductPopularResponse temp = new ProductPopularResponse((String)obj[1], popular);
                products.add(temp);
                // System.out.println(obj[0]+","+obj[1] + ","+obj[2]  );
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return products;
    }


}



