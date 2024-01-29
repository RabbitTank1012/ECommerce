package com.dbn.onlineshopping.domain.response;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPopularResponse {
    private String product;
    private Integer quantity;
}
