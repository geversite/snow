package com.snowfiled.mapper;

import com.snowfiled.bean.PeopleTimeData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface PayMapper {

    // TODO: 2023/4/3 不知道对不对
    @Select("select count(*) from pay inner join window where windowID = #{windowID} and payTime between #{date} and #{fin}")
    int getPeopleAndTimeByWindowID(int windowID, Date date,Date fin);

}
