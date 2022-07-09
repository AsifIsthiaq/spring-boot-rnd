package com.springdemo.springbootrnd.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@JsonIgnoreProperties(value = {"id"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id", length = 100, nullable = false, unique = true)
    private UUID userId;
    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotBlank
    private String username;
    @Column(name = "full_name", length = 100, nullable = false)
    @NotBlank
    private String fullName;
    @Column(name = "password", length = 100, nullable = false)
    @NotBlank
    private String password;
    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotBlank
    private String email;
    @Column(name = "phone", length = 100, nullable = true, unique = true)
    private String phone;

    public User() {
    }

    public User(Long id,
                @JsonProperty("userId") UUID userId,
                @JsonProperty("username") String username,
                @JsonProperty("fullName") String fullName,
                @JsonProperty("password") String password,
                @JsonProperty("email") String email,
                @JsonProperty("phone") String phone) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
