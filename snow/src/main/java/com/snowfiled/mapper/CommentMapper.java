package com.snowfiled.mapper;

import com.snowfiled.bean.Comment;
import com.snowfiled.bean.PeopleTimeData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment")
    List<Comment> getComments();

    @Select("select * from comment order by #{order} #{desc} limit #{st},20")
    List<Comment> getCommentsByOrderAndPage(String order,String desc,int st);

    @Select("select * from comment where windowID = #{windowID} order by #{order} #{desc} limit #{st},20")
    List<Comment> getCommentsByWindowIDAndOrderAndPage(int windowID,String order,String desc,int st);


}
