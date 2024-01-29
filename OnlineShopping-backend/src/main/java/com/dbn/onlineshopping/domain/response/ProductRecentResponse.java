package com.dbn.onlineshopping.domain.response;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRecentResponse {
    private String product;
    private String date;
}
