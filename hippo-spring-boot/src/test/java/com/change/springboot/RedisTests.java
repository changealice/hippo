package com.change.springboot;

import com.change.SampleSimpleApplication;
import com.change.domain.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: change.long
 * Date: 16/5/18
 * Time: 下午5:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SampleSimpleApplication.class)
public class RedisTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(stringRedisTemplate);
    }

    @Test
    public void testStringRedisTemplate() throws Exception {
        String key = "spring-boot:redis-demo";
        String value = "Greeting Redis";
        stringRedisTemplate.opsForValue().set(key, value);
        Assert.assertEquals(value, stringRedisTemplate.opsForValue().get(key));
    }

    @Test
    public void testRedisObjectTemplate() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUserName("赵三");
        user.setPassword("123");
        String key = "spring-boot:redis-object";
        redisTemplate.opsForValue().set(key, user);
        Assert.assertEquals(user.getUserName(),
                redisTemplate.opsForValue().get(key).getUserName());
    }
}
