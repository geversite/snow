package com.snowfiled.snowservice;

import com.snowfiled.bean.PeopleTimeData;
import com.snowfiled.bean.Window;
import com.snowfiled.mapper.PayMapper;
import com.snowfiled.mapper.UserMapper;
import com.snowfiled.service.WindowService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SnowApplicationTests {

    @Resource
    UserMapper mapper;
    @Resource
    WindowService service;
    @Resource
    PayMapper payMapper;


    @Test
    void contextLoads() {
        System.out.println(mapper.getUserByUserID(1));
        System.out.println(mapper.getUserByUserID(2));
    }

    @Test
    void getPeopleTimeData(){
        System.out.println("hello");
        Window window = new Window();
        window.setWindowID(1);
        long time = System.currentTimeMillis();
        time-=60*1000;
        Date date = new Date(time);
        int a =payMapper.getPeopleAndTimeByWindowID(window.getWindowID(),date,new Date(time+60*1000));
        PeopleTimeData dt = new PeopleTimeData(date,a);
        List<PeopleTimeData> list = window.getPeopleTimeData();
        list.add(dt);
        System.out.println(window);
    }

}
