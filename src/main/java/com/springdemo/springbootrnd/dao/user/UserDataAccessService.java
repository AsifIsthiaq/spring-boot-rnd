package com.springdemo.springbootrnd.dao.user;

import com.springdemo.springbootrnd.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("userdao")
public class UserDataAccessService implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User insertUser(User user) {
        final String sql = "INSERT INTO users (user_id, full_name, password, email, phone) VALUES (?, ?, ?, ?, ?)";
        int n = jdbcTemplate.update(sql,
                user.getUserId(),
                user.getFullName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone());
        System.out.println("New user insertion ->" + n);
        System.out.println("New user added " + user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        final String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            long id = Long.parseLong(resultSet.getString("id"));
            UUID user_id = UUID.fromString(resultSet.getString("user_id"));
            String fullName = resultSet.getString("full_name");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            return new User(id, user_id, fullName, password, email, phone);
        });
    }

    @Override
    public User selectUserById(UUID userId) {
        return null;
    }

    @Override
    public int deleteUserById(UUID userId) {
        return 0;
    }

    @Override
    public int updateUserById(UUID userId, User user) {
        return 0;
    }
}
