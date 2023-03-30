package com.snowfiled.bean;


import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Data
@ToString

public class Canteen {

    int canteenID;
    String canteenName;
    int canteenStatus;
    String canteenMessage;
    String canteenImg;

}
