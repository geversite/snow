package com.snowfiled.mapper;

import com.snowfiled.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("Select * from user where userID = #{userID}")
    User getUserByUserID(String userID);



}
