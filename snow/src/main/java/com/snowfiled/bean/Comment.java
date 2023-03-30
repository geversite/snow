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
}
