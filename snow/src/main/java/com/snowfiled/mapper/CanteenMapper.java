package com.snowfiled.mapper;

import com.snowfiled.bean.Canteen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CanteenMapper {

    @Select("select * from canteen")
    List<Canteen> getCanteens();

    // TODO: 2023/3/31 待修改，不知道这个select语句写的对不对
    @Select("select canteenName from canteen where canteenID=#{canteenID}")
    String getCanteenNameByCanteenID(int canteenID);
}

