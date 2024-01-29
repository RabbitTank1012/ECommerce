package com.dbn.onlineshopping.domain.entity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name="order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", unique = true, nullable = false)
    private Integer item_id;

    @Column(name = "order_id")
    private int order_id;

    @Column(name = "product_id")
    private int product_id;

    @Column(name = "purchased_price")
    private double purchased_price ;

    @Column(name = "quantity")
    private int  quantity;

    @Column(name = "wholesale_price")
    private double  wholesale_price ;

    public OrderItem(int order_id,int product_id,double purchased_price,int quantity,double wholesale_price){
        this.order_id=order_id;
        this.product_id =product_id;
        this.purchased_price =purchased_price;
        this.quantity =quantity;
        this.wholesale_price= wholesale_price;

    }

}
