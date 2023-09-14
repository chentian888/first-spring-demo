package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootTliasApplicationTests {

    @Test
    void contextLoads() {
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjk0NTkzNTUzLCJ1c2VybmFtZSI6ImppbnlvbmcifQ.6K3ycRuqYGbyEVFDQkNtZdOWeBcEP53L1sHDHEDAtiE")
                .getBody();

        System.out.println(claims);
    }

}
