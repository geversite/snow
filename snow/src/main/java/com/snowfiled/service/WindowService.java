package com.snowfiled.service;

import com.snowfiled.bean.Canteen;
import com.snowfiled.bean.Floor;
import com.snowfiled.bean.Window;
import com.snowfiled.mapper.CanteenMapper;
import com.snowfiled.mapper.WindowMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class WindowService {

    @Resource
    WindowMapper windowMapper;
    @Resource
    CanteenMapper canteenMapper;

    public List<Window> windowList(){
        return windowMapper.getWindows();
    }

    public List<Canteen> objectList(){
        List<Canteen> data = new ArrayList<>();
        for(Canteen canteen: canteenMapper.getCanteens()){
            int id = canteen.getCanteenID();
            for(int i=1;i<=3;i++){
                Floor floor = new Floor();
                floor.setWindowFloor(i);
                floor.setChildren(windowMapper.getWindowsByFloorAndCanteenID(i,id));
                canteen.getChildren().add(floor);
            }
        }
        return data;
    }

    public Window windowDetail(int windowID){
        return windowMapper.getWindowByWindowID(windowID);
    }

}
