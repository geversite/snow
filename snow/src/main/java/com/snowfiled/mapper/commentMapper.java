package com.snowfiled.mapper;

import com.snowfiled.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface commentMapper {

    @Select("select * from comment")
    List<Comment> getComments();

}
