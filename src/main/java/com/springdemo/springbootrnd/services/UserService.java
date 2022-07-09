package com.springdemo.springbootrnd.services;

import com.springdemo.springbootrnd.dao.user.UserDao;
import com.springdemo.springbootrnd.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(@Qualifier("userdao") UserDao userDao,
                       PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        System.out.println("Password before encryption " + user.getPassword());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        System.out.println("Password after encryption " + user.getPassword());
        return userDao.addUser(user);
    }

    public List<User> getUsers(){
        return this.userDao.getUsers();
    }
}
