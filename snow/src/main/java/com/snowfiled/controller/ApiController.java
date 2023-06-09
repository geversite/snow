package com.snowfiled.controller;

import com.snowfiled.bean.*;
import com.snowfiled.responsedata.ResponseData;
import com.snowfiled.service.CommentService;
import com.snowfiled.service.UserService;
import com.snowfiled.service.WindowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @Resource
    UserService userService;
    @Resource
    WindowService windowService;

    @Resource
    CommentService commentService;


    @RequestMapping("/register")
    @ResponseBody
    public ResponseData<User> register(User user) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            //这里对密码进行了hash加密处理
            userService.register(user);
            ResponseData.Builder<User> builder = ResponseData.<User>builder();
            builder.data(user).status(200).message("注册成功");
            log.info(user.getUserID()+user.getUserName() + "注册成功");
            return builder.build();
        } catch (Exception e) {
            ResponseData.Builder<User> builder = ResponseData.<User>builder();
            builder.data(user).status(500).message(e.getMessage());
            log.info(user.getUserID()+user.getUserName() + "注册失败");
            return builder.build();
        }
    }

    @RequestMapping("/get_login_info")
    @ResponseBody
    public ResponseData<User> get_login_info(int UserID) {
        ResponseData.Builder<User> builder = ResponseData.<User>builder();
        User user = userService.getLoginInfo(UserID);
        if (user==null)
            return builder.data(user).status(2).message("用户不存在").build();
        return builder.data(user).successs().message("获取成功").build();
    }

    @RequestMapping("/windows_list")
    @ResponseBody
    public ResponseData<List<Window>> windows_list() {
        ResponseData.Builder<List<Window>> builder = ResponseData.<List<Window>>builder();
        return builder.data(windowService.windowList()).successs().message("获取成功").build();

    }

    @RequestMapping("/objects_list")
    @ResponseBody
    public ResponseData<List<Canteen>> objects_list() {
        ResponseData.Builder<List<Canteen>> builder = ResponseData.<List<Canteen>>builder();
        return builder.data(windowService.objectList()).successs().message("获取成功").build();
    }

    @RequestMapping("/window_detail")
    @ResponseBody
    public ResponseData<Window> window_detail(int windowID) {
        ResponseData.Builder<Window> builder = ResponseData.<Window>builder();
        return builder.data(windowService.windowDetail(windowID)).successs().message("获取成功").build();
    }

    @RequestMapping("/windows")
    @ResponseBody
    public ResponseData<List<Window>> windows() {
        // TODO: 2023/3/31 这里要将windows_list改成windowsRecommend
        ResponseData.Builder<List<Window>> builder = ResponseData.<List<Window>>builder();
        return builder.data(windowService.windowList()).successs().message("获取成功").build();
    }



    @RequestMapping("/comments")
    @ResponseBody
    public ResponseData<List<Comment>> comments(String order, Boolean desc, Integer page){
        if (Objects.equals(order, ""))
            order="comment_time";
        if (desc==null)
            desc=true;
        if (page==null)
            page=1;
        ResponseData.Builder<List<Comment>> builder = ResponseData.<List<Comment>>builder();
        return builder.data(commentService.comments(order,desc,page)).successs().message("获取成功").build();
    }

    @RequestMapping("/window_comments")
    @ResponseBody
    public ResponseData<List<Comment>> window_comments(int windowID, String order, Boolean desc,Integer page){
        if (Objects.equals(order, ""))
            order="comment_time";
        if (desc==null)
            desc=true;
        if (page==null)
            page=1;
        ResponseData.Builder<List<Comment>> builder = ResponseData.<List<Comment>>builder();
        return builder.data(commentService.windowComments(windowID,order,desc,page)).successs().message("获取成功").build();
    }



}

