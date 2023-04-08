package com.snowfiled.controller;


import com.snowfiled.bean.User;
import com.snowfiled.responsedata.ResponseData;
import com.snowfiled.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("")
    @ResponseBody
    public ResponseData<User> userDetail(String userID){

        return null;
    }





}
