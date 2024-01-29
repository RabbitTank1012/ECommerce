package com.dbn.onlineshopping.domain.entity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false)
    private Integer order_id;

    @Column(name = "date_created")
    private String date_created ;

    @Column(name = "order_status")
    private String   order_status;

    @Column(name = "user_id")
    private int user_id;

    public Orders(String date_created, String order_status, int user_id) {
        this.date_created = date_created;
        this.order_status= order_status;
        this.user_id =user_id;
    }



}
