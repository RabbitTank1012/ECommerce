package com.dbn.onlineshopping.dao;
import com.dbn.onlineshopping.domain.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    SessionFactory sessionFactory;
    private final UserDao userDao;

    public OrderDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<Orders> getAllOrders() {
        Session session;
        List<Orders> orders =new ArrayList<Orders>();
        String userrole = BaseUserInfo.role;
        int user_id= userDao.getUserByname(BaseUserInfo.username).getUser_id();

            try {
                session = sessionFactory.getCurrentSession();
                String sql;
                Query query;
                if (userrole.equals("user")) {
                    sql = "select order.order_id, order.date_created,order.order_status,order.user_id from Orders order  where order.user_id  = :id ";
                    query = session.createQuery(sql);
                    query.setParameter("id", user_id);
                } else {
                    sql = "select order.order_id, order.date_created,order.order_status,order.user_id from Orders order";
                    query = session.createQuery(sql);
                }
                List<Object> resultList = query.list();

                for (int i = 0; i < resultList.size(); i++) {
                    Object[] obj = (Object[]) resultList.get(i);
                    orders.add(new Orders((int) obj[0], (String) obj[1], (String) obj[2], (int) obj[3]));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return orders;
        }

    public Orders GetOrderDetailById(int id){
        Session session;
        Orders result = null;
        try{
            session = sessionFactory.getCurrentSession();
            String sql = "select order.order_id, order.date_created,order.order_status,order.user_id from Orders order  where order.order_id = :id ";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            List<Object> resultList =query.list();

            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[])resultList.get(i);
                result = new Orders((int)obj[0],(String)obj[1],(String)obj[2],(int)obj[3]);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  result;

    }

    public List<OrderItemResult> GetOrderitemlByOrderId(int id){
        Session session;
        List<OrderItemResult> result =new ArrayList<>();
        try{
            session = sessionFactory.getCurrentSession();
            String sql = "select product.name, orderitem.purchased_price, orderitem.quantity, orderitem.wholesale_price " +
                    "from Orders order, OrderItem orderitem, Product product  " +
                    "where order.order_id = orderitem.order_id and product.product_id = orderitem.product_id and  order.order_id = :id ";

            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            List<Object> resultList =query.list();

            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[])resultList.get(i);
                result.add(new OrderItemResult((String)obj[0],(double)obj[1],(int)obj[2],(double)obj[3]));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  result;

    }

    public String AddOrder( List<OrderItemAdd> items){
        Session session;
        String userrole = BaseUserInfo.role;
        String status = null;
        int user_id= userDao.getUserByname(BaseUserInfo.username).getUser_id();
        try{
            session = sessionFactory.getCurrentSession();
            StringBuilder temp = new StringBuilder();
            boolean enoughProduct =true;
            for(OrderItemAdd item: items) {
                int product_id = item.getProduct_id();
                int quantity = item.getQuantity();
                Product product= session.get(Product.class, product_id);
                if(product.getQuantity()< quantity){
                    temp.append(product.getName() + ":"  + product.getQuantity()+ "\n");
                    enoughProduct =false;
                }
            }
            if(!enoughProduct){
                temp.append( "Insufficient stock");
                status = temp.toString();
                return status;
            }

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = new Date();
            String startTime = df.format(date);

            //save order
            Orders orders = new Orders(startTime,"proceeding", user_id);
            Integer order_id = (Integer)session.save(orders);

            //save order_item
            List<OrderItem> orderItems = new ArrayList<>();
            for(OrderItemAdd item: items){
                int product_id = item.getProduct_id();
                int  quantity = item.getQuantity();

                String sql = "select product.retail_price,product.whole_price from Product product  where  product.product_id = :id ";
                Query query = session.createQuery(sql);
                query.setParameter("id", product_id);

                List<Object> resultList =query.list();
                Object[] obj = (Object[])resultList.get(0);
                double retail_price = (double)obj[0];
                double whole_price = (double)obj[1];
                OrderItem orderItem = new OrderItem(order_id,product_id,retail_price,quantity,whole_price);
                session.save(orderItem);

                //the product’s stock should be decremented accordingly
                Product product= session.get(Product.class, product_id);
                product.setQuantity(product.getQuantity()-quantity );
                session.saveOrUpdate(product);

            }


        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "A new order has been added";
    }

    public String updateOrderStatusToComplete(int id){

        Session session;
        String status = null;
        try{
            session = sessionFactory.getCurrentSession();
            Orders order= session.get(Orders.class, id);
            if(order.getOrder_status().equals("cancel"))
            {
                status = "The canceled order can not been updated to  completed ";

            }
            else{
                order.setOrder_status("completed");
                session.saveOrUpdate(order);
                status = "The order has been updated to completed";
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public String updateOrderStatusToCancel(int id){

        Session session;
        String status = null;
        try{
            session = sessionFactory.getCurrentSession();
            Orders order= session.get(Orders.class, id);
            if(order.getOrder_status().equals("completed"))
            {
                status = "The completed order can not been canceled ";

            }
            else{
                order.setOrder_status("cancel");
                session.saveOrUpdate(order);

                //the product’s stock should be incremented accordingly
                String sql = "select product.product_id, orderitem.quantity " +
                        "from Orders order, OrderItem orderitem, Product product  " +
                        "where order.order_id = orderitem.order_id and product.product_id = orderitem.product_id and  order.order_id = :id ";

                Query query = session.createQuery(sql);
                query.setParameter("id", id);
                List<Object> resultList =query.list();


                for (int i = 0; i < resultList.size(); i++) {
                    Object[] obj = (Object[])resultList.get(i);
                    Product product= session.get(Product.class, (int)obj[0]);
                    product.setQuantity(product.getQuantity()+(int)obj[1] );
                    session.saveOrUpdate(product);
                }
                status = "The order has been canceled";
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

}
