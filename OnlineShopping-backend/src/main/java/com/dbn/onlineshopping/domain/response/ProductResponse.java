package com.dbn.onlineshopping.domain.response;
import com.dbn.onlineshopping.domain.entity.Product;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String message;
    private Product product;
}
