package com.dbn.onlineshopping.domain.entity;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String  password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

   public User(String username, String password, String email, String role){
       this.username = username;
       this.password =password;
       this.email =email;
       this.role =role;
   }

}
