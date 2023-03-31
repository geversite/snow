package com.snowfiled.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ToString
@Data
public class Comment {

    int commentID;
    String commentName;
    int commentStatus;
    Date commentTime;
    String commentText;
    int commentRate;
    int userID;
    int windowID;
    // TODO: 2023/3/31 这里需要提醒一下陈帆，加入windowsID在前端的展示里 
}
