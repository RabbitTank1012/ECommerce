package com.dbn.onlineshopping.domain.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderItemResult {
    String product_name;
    double purchased_price;
    int quantity;
    double wholesale_price;

}
