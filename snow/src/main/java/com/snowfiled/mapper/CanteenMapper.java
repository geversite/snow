package com.snowfiled.mapper;

import com.snowfiled.bean.Canteen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CanteenMapper {

    @Select("select * from canteen")
    List<Canteen> getCanteens();


    @Select("select canteenName from canteen where canteenID=#{canteenID}")
    String getCanteenNameByCanteenID(int canteenID);
}

