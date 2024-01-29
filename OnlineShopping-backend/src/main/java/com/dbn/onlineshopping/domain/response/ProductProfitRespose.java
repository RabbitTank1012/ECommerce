package com.dbn.onlineshopping.domain.response;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductProfitRespose {
    private String product;
    private double profit;
}
