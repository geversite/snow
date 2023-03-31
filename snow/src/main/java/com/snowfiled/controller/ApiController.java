package com.snowfiled.controller;

import com.snowfiled.bean.*;
import com.snowfiled.responsedata.CommentDetail;
import com.snowfiled.responsedata.ResponseData;
import com.snowfiled.responsedata.WindowDetail;
import com.snowfiled.service.CommentService;
import com.snowfiled.service.UserService;
import com.snowfiled.service.WindowService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/aip")
@Log4j
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
            userService.register(user);
            ResponseData.Builder<User> builder = ResponseData.<User>builder();
            builder.data(user).status(200).message("注册成功");
            return builder.build();
        } catch (Exception e) {
            ResponseData.Builder<User> builder = ResponseData.<User>builder();
            builder.data(user).status(500).message(e.getMessage());
            return builder.build();
        }
    }

    @RequestMapping("/get_login_info")
    @ResponseBody
    public ResponseData<User> get_login_info(int UserID) {
        ResponseData.Builder<User> builder = ResponseData.<User>builder();
        return builder.data(userService.getLoginInfo(UserID)).successs().message("获取成功").build();
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
    public ResponseData<WindowDetail> window_detail(int windowID) {
        ResponseData.Builder<WindowDetail> builder = ResponseData.<WindowDetail>builder();
        WindowDetail windowDetail = new WindowDetail(windowService.windowDetail(windowID), windowService.peopleTimeData(windowID));
        return builder.data(windowDetail).successs().message("获取成功").build();
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
    public ResponseData<List<CommentDetail>> comments(String order, boolean desc, int page){
        ResponseData.Builder<List<CommentDetail>> builder = ResponseData.<List<CommentDetail>>builder();
        return builder.data(commentService.commentsDetail(order,desc,page)).successs().message("获取成功").build();
    }

    @RequestMapping("/window_comments")
    @ResponseBody
    public ResponseData<List<Comment>> window_comments(int windowID,String order, boolean desc,int page){
        ResponseData.Builder<List<Comment>> builder = ResponseData.<List<Comment>>builder();
        return builder.data(commentService.windowComments(windowID,order,desc,page)).successs().message("获取成功").build();
    }



}

