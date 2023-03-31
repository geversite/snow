package com.snowfiled.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

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
    String windowImg;

}
