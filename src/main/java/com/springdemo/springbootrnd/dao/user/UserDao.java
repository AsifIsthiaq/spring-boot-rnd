package com.springdemo.springbootrnd.dao.user;

import com.springdemo.springbootrnd.models.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    User insertUser(User user);

    default User addUser(User user){
        user.setUserId(UUID.randomUUID());
        return insertUser(user);
    }

    List<User> selectAllUsers();

    User selectUserById(UUID userId);

    int deleteUserById(UUID userId);

    int updateUserById(UUID userId, User user);
}
