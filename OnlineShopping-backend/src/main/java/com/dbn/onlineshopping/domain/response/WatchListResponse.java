package com.dbn.onlineshopping.domain.response;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WatchListResponse {
    private  Integer watch_id;
    private  Integer product_id;
    private  String  product_name;;

}
