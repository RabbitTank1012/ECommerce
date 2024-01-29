package com.dbn.onlineshopping.domain.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductResult {
    private Integer product_id;
    private String name;
    private String description;
    private double retail_price ;
    private int quantity;
    private double whole_price ;
}
