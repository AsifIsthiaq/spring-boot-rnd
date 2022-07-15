package com.springdemo.springbootrnd.dao.caching;

import com.springdemo.springbootrnd.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisDataAccessService implements RedisDao {
    private RedisTemplate<String, Boolean> redisTemplate;
    @Value("${spring.redis.expire}")
    private int expireTime;

    @Autowired
    public RedisDataAccessService(RedisTemplate<String, Boolean> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(String key, Boolean value) {
        key = Utility.addBlacklistPrefixToToken(key);
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public Boolean isTokenBlacklisted(String key) {
        key = Utility.addBlacklistPrefixToToken(key);
        Boolean value = redisTemplate.opsForValue().get(key);
        return value == null ? false : value;
    }
}
