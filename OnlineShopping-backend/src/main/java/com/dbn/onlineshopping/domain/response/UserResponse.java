package com.dbn.onlineshopping.domain.response;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String message;
    private int userid;
    private String username;
    private String password;
    private String email;
    private String role;
}
