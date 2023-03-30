package com.snowfiled.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ToString
@Data
public class Floor {

    int windowFloor;
    List<Window> children;

}
