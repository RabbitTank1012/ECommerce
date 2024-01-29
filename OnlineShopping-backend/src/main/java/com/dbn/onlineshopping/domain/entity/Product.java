package com.dbn.onlineshopping.domain.entity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true, nullable = false)
    private Integer product_id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;



    @Column(name = "retail_price")
    private double retail_price ;

    @Column(name = "quantity")
    private int quantity ;



    @Column(name = "whole_price")
    private double whole_price ;

  //  public  Product()
     //   new Product((int)obj[0],(String)obj[1],(String)obj[2],(double)obj[3],(int)obj[4],(double)obj[5]);

}
