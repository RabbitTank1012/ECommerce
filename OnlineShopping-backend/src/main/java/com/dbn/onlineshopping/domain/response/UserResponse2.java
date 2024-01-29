package com.dbn.onlineshopping.domain.response;
import com.dbn.onlineshopping.domain.entity.UserResult;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse2 {
    private String message;
    private UserResult userResult;
}
