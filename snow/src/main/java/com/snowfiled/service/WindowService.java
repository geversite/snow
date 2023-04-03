package com.snowfiled.service;

import com.snowfiled.bean.Canteen;
import com.snowfiled.bean.Floor;
import com.snowfiled.bean.PeopleTimeData;
import com.snowfiled.bean.Window;
import com.snowfiled.mapper.CanteenMapper;
import com.snowfiled.mapper.CommentMapper;
import com.snowfiled.mapper.PayMapper;
import com.snowfiled.mapper.WindowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WindowService {

    @Resource
    WindowMapper windowMapper;
    @Resource
    CanteenMapper canteenMapper;
    @Resource
    CommentMapper commentMapper;
    @Resource
    PayMapper payMapper;

    public List<Window> windowList(){
        return proc(windowMapper.getWindows());
    }

    public List<Canteen> objectList(){
        List<Canteen> data = new ArrayList<>();
        for(Canteen canteen: canteenMapper.getCanteens()){
            int id = canteen.getCanteenID();
            for(int i=1;i<=3;i++){
                Floor floor = new Floor();
                floor.setWindowFloor(i);
                floor.setChildren(proc(windowMapper.getWindowsByFloorAndCanteenID(i,id)));
                canteen.getChildren().add(floor);
            }
        }
        return data;
    }

    public Window windowDetail(int windowID){
        return windowMapper.getWindowByWindowID(windowID);
    }



    protected List<Window> proc(List<Window> list){
        for(Window window : list){
            window=proc(window);
        }
        return list;
    }

    protected Window proc(Window window) {
        long time = System.currentTimeMillis();
        for(int i = 1; i<=10;i++){
            time-=60*1000;
            Date date = new Date(time);
            window.getPeopleTimeData().add(new PeopleTimeData(date,payMapper.getPeopleAndTimeByWindowID(window.getWindowID(),date,new Date(time+60*1000))));
        }
        window.setCanteenName(canteenMapper.getCanteenNameByCanteenID(window.getCanteenID()));

        return window;
    }
}
