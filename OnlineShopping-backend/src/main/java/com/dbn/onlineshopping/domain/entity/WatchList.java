package com.dbn.onlineshopping.domain.entity;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name="watchlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "watch_id", unique = true, nullable = false)
    private Integer watch_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "product_id")
    private int product_id;

    public WatchList(int user_id,int product_id){
       this.user_id = user_id;
       this.product_id = product_id;
    }

}
