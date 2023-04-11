package com.snowfiled.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ToString
@Data
public class Window {
    int windowID;
    String windowName;
    int windowStatus;
    String windowMessage;
    int windowFloor;
    int canteenID;
    String canteenName;//附加属性
    String windowImg;
    List<PeopleTimeData> peopleTimeData;//附加属性

    public Window(){
        super();
        peopleTimeData = new ArrayList<>();
    }

}
