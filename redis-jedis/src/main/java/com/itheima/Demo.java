package com.itheima;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        Jedis jedis = JedisConnectionFacotry.getJedis();
//        String
//        jedis.mset("k1", "v1", "k2", "v2", "k3", "v3");
//        List<String> s = jedis.mget("k1", "k2", "k3");
//        System.out.println(s);
        jedis.flushDB();
//        jedis.lpush("key1", "Marry", "Lucy", "Jack");
//        System.out.println(jedis.lrange("key1", 0, -1));

//        jedis.sadd("name","luch","marry");
//        System.out.println(jedis.smembers("name"));

        jedis.hset("users", "age", "20");

        System.out.println(jedis.hget("users","age"));

        Set<String> s = jedis.keys("*");
        for (String i : s) {
            System.out.println(i);
        }
    }
}
