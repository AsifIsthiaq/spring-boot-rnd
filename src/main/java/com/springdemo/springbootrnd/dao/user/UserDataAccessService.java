package com.springdemo.springbootrnd.dao.user;

import com.springdemo.springbootrnd.exception.UserAlreadyExistException;
import com.springdemo.springbootrnd.models.User;
import com.springdemo.springbootrnd.util.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
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
        final String sql = "INSERT INTO users (user_id, username, password, full_name, email, phone, photo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int n = jdbcTemplate.update(sql,
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getPhoto());
        System.out.println("New user insertion ->" + n);
        System.out.println("New user added " + user);
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        final String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return UserUtility.getUserFromResultSet(resultSet);
        });
    }

    @Override
    public User selectUserById(UUID userId) {
        final String sql = "SELECT * FROM users where user_id = ?";
        Object[] obj = new Object[]{userId};
        User user = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            return UserUtility.getUserFromResultSet(resultSet);
        }, obj);
        System.out.println("After querying selectUserById: " + user);
        return user;
    }

    @Override
    public User selectUserByUsername(String username) throws BadCredentialsException, DataAccessException {
        final String sql = "SELECT * FROM users where username = ?";
        Object[] obj = new Object[]{username};
        try {
            User user = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
                return UserUtility.getUserFromResultSet(resultSet);
            }, obj);
            System.out.println("After querying selectUserByUsername: " + user);
            return user;
        } catch (DataAccessException e) {
            System.out.println("selectUserByUsername exception occurred: " + e);
            throw new BadCredentialsException("Incorrect username or password", e);
        }
    }

    @Override
    public void checkIfUsernameAlreadyExists(String username) throws DataAccessException, UserAlreadyExistException {
        final String sql = "SELECT * FROM users where username = ?";
        Object[] obj = new Object[]{username};
        try {
            User user = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
                return UserUtility.getUserFromResultSet(resultSet);
            }, obj);
            System.out.println("checkIfUsernameAlreadyExists: " + user);
            throw new UserAlreadyExistException("User already exists please use a different username", new Throwable());
        } catch (DataAccessException e) {
            System.out.println("checkIfUsernameAlreadyExists exception occurred: " + e);
        }
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
