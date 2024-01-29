package com.dbn.onlineshopping.domain.response;

import com.dbn.onlineshopping.domain.entity.OrderItemResult;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemResponse {
    private String message;
    private OrderItemResult orderitem;
}
