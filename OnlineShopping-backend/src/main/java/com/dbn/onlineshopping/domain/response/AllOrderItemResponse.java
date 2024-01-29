package com.dbn.onlineshopping.domain.response;

import com.dbn.onlineshopping.domain.entity.OrderItemResult;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllOrderItemResponse {
    private String message;
    private List<OrderItemResult> orderitems;
}
