package com.snowfiled.snowservice;

import com.snowfiled.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SnowApplicationTests {

    @Resource
    UserMapper mapper;
    @Test
    void contextLoads() {
        System.out.println(mapper.getUserByUserID(1));
        System.out.println(mapper.getUserByUserID(2));
    }

}
