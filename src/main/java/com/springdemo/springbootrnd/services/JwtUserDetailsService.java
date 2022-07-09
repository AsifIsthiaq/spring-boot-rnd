package com.springdemo.springbootrnd.services;

import java.util.ArrayList;

import com.springdemo.springbootrnd.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private UserDao userDao;

    @Autowired
    public JwtUserDetailsService(@Qualifier("userdao") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("JwtUserDetailsService: loadUserByUsername-> " + username);
        com.springdemo.springbootrnd.models.User user = this.userDao.selectUserByUsername(username);
        if (user != null && user.getUsername().equals(username)) {
            return new User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
        } else {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}