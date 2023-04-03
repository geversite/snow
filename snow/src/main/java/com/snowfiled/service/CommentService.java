package com.snowfiled.service;

import com.snowfiled.bean.Comment;
import com.snowfiled.bean.User;
import com.snowfiled.mapper.CommentMapper;
import com.snowfiled.mapper.UserMapper;
import com.snowfiled.mapper.WindowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {

    @Resource
    CommentMapper commentMapper;
    @Resource
    WindowMapper windowMapper;
    @Resource
    UserMapper userMapper;

    public List<Comment> comments(String order,boolean desc,int page){
        return proc(commentMapper.getCommentsByOrderAndPage(order,desc?"desc":"asc",(page-1)*20));
    }


    public List<Comment> windowComments(int windowID,String order,boolean desc,int page){
        return proc(commentMapper.getCommentsByWindowIDAndOrderAndPage(windowID,order,desc?"desc":"asc",(page-1)*20));
        // TODO: 2023/3/31 这里也要把comment转换成commentDetail
    }

    protected List<Comment> proc(List<Comment> list){
        for(Comment comment:list){
            User user = userMapper.getUserByUserID(comment.getUserID());
            comment.setUserImg(user.getUserImg());
            comment.setUserName(user.getUserName());
            comment.setWindowName(windowMapper.getWindowByWindowID(comment.getWindowID()).getWindowName());
        }
        return list;
    }


}
