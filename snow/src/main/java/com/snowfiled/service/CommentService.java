package com.snowfiled.service;

import com.snowfiled.bean.Comment;
import com.snowfiled.mapper.CommentMapper;

import javax.annotation.Resource;
import java.util.List;

public class CommentService {

    @Resource
    CommentMapper commentMapper;

    public List<Comment> comments(String order,boolean desc,int page){
        return commentMapper.getCommentsByOrderAndPage(order,desc?"desc":"asc",(page-1)*20);
    }


    public List<Comment> windowComments(int windowID,String order,boolean desc,int page){
        return commentMapper.getCommentsByWindowIDAndOrderAndPage(windowID,order,desc?"desc":"asc",(page-1)*20);
    }


}
