package com.itheima;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class SpringRedisApplicationTests {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() throws JsonProcessingException {
//        User user = new User("chentian", 18);
//        String userString = mapper.writeValueAsString(user);
//        stringRedisTemplate.opsForValue().set("user:300", userString);
//        String getUser = stringRedisTemplate.opsForValue().get("user:300");
//        User userJson = mapper.readValue(getUser, User.class);
//        System.out.println("user = " + userJson);
        stringRedisTemplate.opsForHash().put("user:400", "name", "chentian");
        stringRedisTemplate.opsForHash().put("user:400", "age", "18");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println(entries);
    }

}
