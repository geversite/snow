package com.snowfiled.mapper;

import com.snowfiled.bean.Window;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface windowMapper {

    @Select("select * from window")
    List<Window> getWindows();

}
