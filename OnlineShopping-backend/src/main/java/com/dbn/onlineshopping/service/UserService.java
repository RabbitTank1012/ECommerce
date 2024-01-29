package com.dbn.onlineshopping.service;
import com.dbn.onlineshopping.dao.UserDao;

import com.dbn.onlineshopping.domain.entity.BaseUserInfo;
import com.dbn.onlineshopping.domain.entity.User;
import com.dbn.onlineshopping.domain.entity.UserResult;
import com.dbn.onlineshopping.exception.UserSaveFailedException;
import com.dbn.onlineshopping.security.AuthUserDetail;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public List<UserResult> getAllUsers2(){
        return userDao.getAllUsers2();
    }

    public User getUserById(int id){
        return userDao.getUserById(id);
    }

    public UserResult getUserById2(int id){
        return  userDao.getUserById2(id);
    }

    public String saveUserSuccess(User user){
        return userDao.addUser(user);
    }

    @Transactional(rollbackFor = UserSaveFailedException.class)
    public void saveUserFailed(User user) throws UserSaveFailedException {
        //success operation
        userDao.addUser(user);
        //failed operation
        userDao.somethingWentWrong();

    }

    public String addUser(User user){

        return userDao.addUser(user);
    }

    public void deleteUser(int id){
        User user = userDao.getUserById(id);
        userDao.deleteById(user);
    }

    public void updateStatusById(int userid, boolean status){
        userDao.updateStatusById(userid,status);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("enter" );
        User user = userDao.getUserByname(username);
        if (user == null){
            throw new UsernameNotFoundException("Username does not exist");
        }
        BaseUserInfo.role = user.getRole();
        System.out.println("UserPass:" + user.getPassword() );

        return AuthUserDetail.builder() // spring security's userDetail
                .username(user.getUsername())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .authorities(getAuthoritiesFromUser(user))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    private List<GrantedAuthority> getAuthoritiesFromUser(User user){
        List<GrantedAuthority> userAuthorities = new ArrayList<>();

        userAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

        return userAuthorities;
    }

}
