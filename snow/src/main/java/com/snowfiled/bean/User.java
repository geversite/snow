package com.snowfiled.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
public class User {
    int userID;
    String userName;
    int userStatue;
    String password;
    String userImg;
    String userMessage;

}
