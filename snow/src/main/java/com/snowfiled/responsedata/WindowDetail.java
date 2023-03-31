package com.snowfiled.responsedata;

import com.snowfiled.bean.PeopleTimeData;
import com.snowfiled.bean.Window;
import com.snowfiled.mapper.CanteenMapper;
import com.snowfiled.service.WindowService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Data
public class WindowDetail {
    int windowID;
    String windowName;
    int windowStatus;
    int windowFloor;
    int canteenName;
    // TODO: 2023/3/31 要不要改成canteenName
    List<PeopleTimeData> peopleTimeData;



    public WindowDetail(Window window, List<PeopleTimeData> peopleTimeData){
        this.windowID = window.getWindowID();
        this.windowName = window.getWindowName();
        this.windowStatus = window.getWindowStatus();
        this.windowFloor = window.getWindowFloor();
        this.canteenName = window.getCanteenID();
    }

    private String getCanteenName(int canteenID){
        CanteenMapper canteenMapper = null;
        return canteenMapper.getCanteenNameByCanteenID(canteenID);
    }


}