package com.snowfiled.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class DataToSend {

    int status = 0;
    Object data;
    String message = "请求成功";

    public DataToSend(Object data){
        this();
        this.data = data;
    }

}
