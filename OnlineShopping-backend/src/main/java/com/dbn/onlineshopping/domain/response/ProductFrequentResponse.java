package com.dbn.onlineshopping.domain.response;


import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFrequentResponse {
    private String product;
    private Integer frequent;
}
