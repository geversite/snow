package com.snowfiled.mapper;

import com.snowfiled.bean.Window;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WindowMapper {

    @Select("select * from window")
    List<Window> getWindows();

    @Select("select * from window where windowFloor = #{windowFloor} and canteenID = #{canteenID}")
    List<Window> getWindowsByFloorAndCanteenID(int windowFloor, int canteenID);


    @Select("select * from window where windowID = #{windowID}")
    Window getWindowByWindowID(int windowID);

}
