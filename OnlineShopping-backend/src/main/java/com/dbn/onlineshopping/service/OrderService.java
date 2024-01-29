package com.dbn.onlineshopping.service;
import com.dbn.onlineshopping.domain.entity.Orders;
import com.dbn.onlineshopping.dao.OrderDao;

import com.dbn.onlineshopping.domain.entity.OrderItemAdd;
import com.dbn.onlineshopping.domain.entity.OrderItemResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Orders> getAllOrders(){
        return orderDao.getAllOrders();
    }

    public Orders GetOrderDetailById(int id){
        return orderDao.GetOrderDetailById(id);
    }

    public List<OrderItemResult> GetOrderitemlByOrderId(int id){
        return orderDao.GetOrderitemlByOrderId(id);
    }
    public String AddOrder( List<OrderItemAdd> items){
        return orderDao.AddOrder(items);
    }
    public String updateOrderStatusToComplete(int id){
        return orderDao.updateOrderStatusToComplete(id);
    }

    public String updateOrderStatusToCancel(int id){
        return  orderDao.updateOrderStatusToCancel(id);
    }
}
