package com.springdemo.springbootrnd.dao.caching;

public interface RedisDao {
    public void save(String key, Boolean value);

    public Boolean isTokenBlacklisted(String key);
}
