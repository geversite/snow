package com.snowfiled.service;

import com.snowfiled.bean.Comment;
import com.snowfiled.mapper.CommentMapper;
import com.snowfiled.responsedata.CommentDetail;

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
        // TODO: 2023/3/31 这里也要把comment转换成commentDetail
    }


    public List<CommentDetail> commentsDetail(String order, boolean desc, int page) {
        // TODO: 2023/3/31 把comments转换成commentDetail,对于windows的包装我在controller里面做掉了

        return null;
    }
}
