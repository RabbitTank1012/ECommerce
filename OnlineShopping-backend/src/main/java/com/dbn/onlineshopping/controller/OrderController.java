package com.dbn.onlineshopping.controller;
import com.dbn.onlineshopping.domain.entity.Orders;
import com.dbn.onlineshopping.domain.response.Hintmessage;
import com.dbn.onlineshopping.domain.entity.OrderItemAdd;
import com.dbn.onlineshopping.domain.entity.OrderItemResult;
import com.dbn.onlineshopping.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;
    private String role = "user";

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(("/all"))
    public List<Orders> getAllProducts() {
        return orderService.getAllOrders();
    }


    @GetMapping(("/{id}"))
    public List<OrderItemResult> GetOrderDetailById(@PathVariable int id){

        List<OrderItemResult> orderitems = orderService.GetOrderitemlByOrderId(id);
        return orderitems;
    }

    @PostMapping()
    public Hintmessage saveOrderSuccess(@RequestBody List<OrderItemAdd> order){

        System.out.println("Post a order");
        String status =orderService.AddOrder(order);
        return Hintmessage.builder()
                .message(status)
                .build();
    }

    @PatchMapping("/{id}/complete")
    public Hintmessage updateOrderToComplete(@PathVariable int id) {
        String status = orderService.updateOrderStatusToComplete(id);
        return Hintmessage.builder()
                .message(status)
                .build();
    }


    @PatchMapping("/{id}/cancel")
    public Hintmessage updateOrderToCancel(@PathVariable int id) {
        System.out.println("Cancel a order");
        System.out.println("id =" + id);
        String status = orderService.updateOrderStatusToCancel(id);

        return Hintmessage.builder()
                .message(status)
                .build();

    }

}
