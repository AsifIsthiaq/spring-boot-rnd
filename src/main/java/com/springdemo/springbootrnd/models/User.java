package com.springdemo.springbootrnd.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

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
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
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
    @Column(name = "phone", length = 100)
    private String phone;
    @Column(name = "photo", length = 100)
    private String photo;

    public User() {
    }

    public User(Long id,
                @JsonProperty("userId") UUID userId,
                @JsonProperty("username") String username,
                @JsonProperty("fullName") String fullName,
                @JsonProperty("password") String password,
                @JsonProperty("email") String email,
                @JsonProperty("phone") String phone,
                @JsonProperty("photo") String photo) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
                ", photo='" + photo + '\'' +
                '}';
    }
}
