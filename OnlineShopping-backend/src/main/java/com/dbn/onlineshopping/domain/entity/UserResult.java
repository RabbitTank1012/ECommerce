package com.dbn.onlineshopping.domain.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserResult {
    private int userid;
    private String username;
    private String password;
    private String email;
    private String role;

}
