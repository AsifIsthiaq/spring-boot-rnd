package com.springdemo.springbootrnd.util;

import com.springdemo.springbootrnd.models.User;
import org.springframework.dao.DataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserUtility {
    public static User getUserFromResultSet(ResultSet resultSet) throws SQLException, DataAccessException {
        long id = Long.parseLong(resultSet.getString("id"));
        UUID user_id = UUID.fromString(resultSet.getString("user_id"));
        String username = resultSet.getString("username");
        String fullName = resultSet.getString("full_name");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        return new User(id, user_id, username, fullName, password, email, phone);
    }
}
