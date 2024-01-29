package com.dbn.onlineshopping.domain.response;
import com.dbn.onlineshopping.domain.entity.Orders;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String message;
    private Orders order;
}
