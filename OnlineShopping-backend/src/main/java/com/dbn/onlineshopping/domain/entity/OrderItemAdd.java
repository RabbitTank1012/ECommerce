package com.dbn.onlineshopping.domain.entity;
import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderItemAdd {

    private int product_id;
    private int  quantity;

}
