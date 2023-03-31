package com.snowfiled.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Data@ToString
public class Dish {

    int dishID;
    String dishName;
    int dishStatus;
    int windowID;
    float dishValue;
}
