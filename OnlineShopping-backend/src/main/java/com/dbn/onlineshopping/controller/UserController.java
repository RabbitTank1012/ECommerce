package com.dbn.onlineshopping.controller;
import com.dbn.onlineshopping.domain.entity.User;
import com.dbn.onlineshopping.domain.entity.UserLogin;
import com.dbn.onlineshopping.domain.response.LoginResponse;
import com.dbn.onlineshopping.domain.response.UserResponse;
import com.dbn.onlineshopping.domain.response.UserResponse2;
import com.dbn.onlineshopping.service.UserService;
import com.dbn.onlineshopping.domain.entity.BaseUserInfo;
import com.dbn.onlineshopping.domain.entity.UserResult;
import com.dbn.onlineshopping.security.AuthUserDetail;
import com.dbn.onlineshopping.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping()
public class UserController {

    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private JwtProvider jwtProvider;

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserResult> getAllUsersSuccess() {
        return userService.getAllUsers2();
    }

    @GetMapping("/")
    public UserResponse2 getUserById(@RequestParam int userId) {
        UserResult userResult = userService.getUserById2(userId);
        return UserResponse2.builder()
                .message("Returning user with id: " + userId)
                .userResult(userResult)
                .build();

    }

    @PostMapping("/signup")
    public UserResponse saveUserSuccess(@RequestBody User user){
        userService.saveUserSuccess(user);
        return UserResponse.builder()
                .message("Add a new user")
                .userid(user.getUser_id())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


    @PostMapping("/login")
    public LoginResponse Login(@RequestBody UserLogin request) {

        Authentication authentication;
        System.out.println("Username:" + request.getUsername() + " Password: " + request.getPassword());
        //Try to authenticate the user using the username and password
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Provided credential is invalid.");
        }
        //Successfully authenticated user will be stored in the authUserDetail object
        AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal(); //getPrincipal() returns the user object

        //A token wil be created using the username/email/userId and permission
        String token = jwtProvider.createToken(authUserDetail);
        //Returns the token as a response to the frontend/postman
        return LoginResponse.builder()
              //  .message("Welcome " + authUserDetail.getUsername())
                .message(BaseUserInfo.role)
                .token(token)
                .build();


    }

}
