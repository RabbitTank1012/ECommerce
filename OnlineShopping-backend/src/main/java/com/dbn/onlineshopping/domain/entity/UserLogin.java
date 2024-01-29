package com.dbn.onlineshopping.domain.entity;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogin {
    private String username;
    private String  password;
}
