package com.snowfiled.mapper;

import com.snowfiled.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("Select * from user where userID = #{userID}")
    User getUserByUserID(int userID);

    @Insert("insert into user (userID,userName,userStatus,password,userImg,userMassage) values (#{userID},#{userName},#{userStatus},#{password},#{userImg},#{useMessage})")
    int addUser(User user);



}
